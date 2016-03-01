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

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.util.DaoProvider;

/**
 * 
 * @author Edouard
 *
 */
public class AuthenticationService {
	
	public static final String HEADER_KEY = "Authorization";
	
	private Dao<SecurId, String> securidDao;
	private Dao<Member, Integer> memberDao;
	private Member principal;
	private boolean secured;
	
	public AuthenticationService() {
		this.securidDao = DaoProvider.getDao(SecurId.class);
		this.memberDao = DaoProvider.getDao(Member.class);
	}
	
	public void authenticate(ContainerRequestContext requestContext, String authentication) throws SQLException {
		if (Strings.isNullOrEmpty(authentication)) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		else {
			SecurId securId = securidDao.queryBuilder().where().eq("token", authentication).queryForFirst();
			if (securId == null) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
			else {
				if (securId.hasExpire()) {
					securId.regenerate();
					securidDao.update(securId);
				}
				principal = memberDao.queryBuilder().where().eq("login", securId.getId()).queryForFirst();
				secured = requestContext.getSecurityContext().isSecure();
				requestContext.getHeaders().putSingle(HEADER_KEY, securId.getToken());
				requestContext.setSecurityContext(new RoleSecurityContext());
			}
		}
	}
	
	private final class RoleSecurityContext implements SecurityContext {
		
		public RoleSecurityContext() {}

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
