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

import static fr.da2i.lup1.security.AuthenticationService.HEADER_KEY;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Authenticated
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	private AuthenticationService authenticationService;
	
	public AuthenticationFilter() {
		this.authenticationService = new AuthenticationService();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().putSingle(HEADER_KEY, requestContext.getHeaderString(HEADER_KEY));
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final String authentication = requestContext.getHeaderString(HEADER_KEY);
		try {
			authenticationService.authenticate(requestContext, authentication);
		} catch (SQLException e) {
			e.printStackTrace();
			requestContext.abortWith(Response.serverError().build());
		}
	}
	
	

}
