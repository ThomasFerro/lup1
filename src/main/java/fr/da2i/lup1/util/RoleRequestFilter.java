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

import static fr.da2i.lup1.util.SecurId.CLIENT_TOKEN_KEY;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.Member;

@Provider
@Priority(Priorities.AUTHORIZATION)
@Authenticated
public class RoleRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		requestContext.setSecurityContext(new RoleSecurityContext(requestContext));
	}
	
	private static final class RoleSecurityContext implements SecurityContext {
		
		private Dao<SecurId, String> securidDao = DaoProvider.getDao(SecurId.class);
		private Dao<Member, Integer> memberDao = DaoProvider.getDao(Member.class);
		
		private SecurId securId;
		private Member member;
		private final String authentication;
		
		public RoleSecurityContext(ContainerRequestContext requestContext) {
			this.authentication = requestContext.getHeaderString(CLIENT_TOKEN_KEY);
			try {
				this.securId = securidDao.queryBuilder().where().eq("token", authentication).queryForFirst();
				this.member = memberDao.queryBuilder().where().eq("login", securId.getId()).queryForFirst();
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isUserInRole(String role) {
			return member.getRole().equals(role);
		}
		
	}

}
