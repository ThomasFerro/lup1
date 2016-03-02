/**
 * This file is part of lup1.
 *
 * lup1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * lup1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with lup1.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.da2i.lup1.security;

import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;

import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.util.DaoProvider;

/**
 * 
 * @author Edouard
 *
 */
@Singleton
public class AuthenticationService {
	
	public static final String HEADER_KEY = "Authorization";
	
	private Dao<SecureKey, String> secureKeyDao;
	private Dao<Member, Integer> memberDao;
	
	public AuthenticationService() {
		this.secureKeyDao = DaoProvider.getDao(SecureKey.class);
		this.memberDao = DaoProvider.getDao(Member.class);
	}
	
	private void abort(ContainerRequestContext requestContext) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}
	
	public void authenticate(ContainerRequestContext requestContext, String authentication) throws SQLException, ParseException, JOSEException {
		SignedJWT signedJWT;
		if (Strings.isNullOrEmpty(authentication) || (signedJWT = SecureKey.parse(authentication)) == null) {
			abort(requestContext);
		}
		else {
			JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			SecureKey secureKey = secureKeyDao.queryForId(claimsSet.getIssuer());
			if (secureKey == null || !secureKey.verify(signedJWT)) {
				abort(requestContext);
			}
			else {
				if (secureKey.hasExpire(signedJWT)) {
					signedJWT = secureKey.regenerate();
					secureKeyDao.update(secureKey);
				}
				Member principal = memberDao.queryBuilder().where().eq("login", secureKey.getId()).queryForFirst();
				boolean secured = requestContext.getSecurityContext().isSecure();
				requestContext.getHeaders().putSingle(HEADER_KEY, signedJWT.serialize());
				requestContext.setSecurityContext(new RoleSecurityContext(principal, secured));
			}
		}
	}
	
	private final class RoleSecurityContext implements SecurityContext {
		
		private Member principal;
		private boolean secured;
		
		public RoleSecurityContext(Member principal, boolean secured) {
			this.principal = principal;
			this.secured = secured;
		}

		@Override
		public String getAuthenticationScheme() {
			return SecurityContext.FORM_AUTH;
		}

		@Override
		public Principal getUserPrincipal() {
			return principal;
		}

		@Override
		public boolean isSecure() {
			return secured;
		}

		@Override
		public boolean isUserInRole(String role) {
			if (principal == null) {
				return false;
			}
			return principal.getRole().equals(role);
		}
		
	}

}
