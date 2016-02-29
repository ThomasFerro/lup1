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
package fr.da2i.lup1.util;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.Member;

import static fr.da2i.lup1.util.SecurId.*;

@Deprecated
@Provider
@Priority(Priorities.AUTHORIZATION)
@Authenticated
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	private Dao<SecurId, String> securidDao = DaoProvider.getDao(SecurId.class);

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add(CLIENT_TOKEN_KEY, requestContext.getHeaderString(CLIENT_TOKEN_KEY));	
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final String authentication = requestContext.getHeaderString(CLIENT_TOKEN_KEY);
		if (Strings.isNullOrEmpty(authentication)) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		else {
			try {
				SecurId securId = securidDao.queryBuilder().where().eq("token", authentication).queryForFirst();
				if (securId == null) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				}
				else {
					if (securId.hasExpire()) {
						securId.regenerate();
						securidDao.update(securId);
					}
					requestContext.getHeaders().add(CLIENT_TOKEN_KEY, securId.getToken());
					requestContext.setSecurityContext(new RoleSecurityContext(securId));
				}
			} catch (SQLException e) {
				requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
			}
		}
	}
	
	private static final class SimplePrincipal implements Principal {
		
		private String role;
		private String name;
		
		public SimplePrincipal(Member member) {
			this.role = member.getRole();
			this.name = member.getLogin();
		}

		@Override
		public String getName() {
			return name;
		}
		
		public String getRole() {
			return role;
		}
		
	}
	
	private static final class RoleSecurityContext implements SecurityContext {
		
		private Dao<Member, Integer> memberDao = DaoProvider.getDao(Member.class);
		
		private SimplePrincipal principal;
		
		public RoleSecurityContext(SecurId securId) {
			try {
				Member member = memberDao.queryBuilder().where().eq("login", securId.getId()).queryForFirst();
				this.principal = new SimplePrincipal(member);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String getAuthenticationScheme() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Principal getUserPrincipal() {
			return principal;
		}

		@Override
		public boolean isSecure() {
			return true;
		}

		@Override
		public boolean isUserInRole(String role) {
			return principal.getRole().equals(role);
		}
		
	}

}
