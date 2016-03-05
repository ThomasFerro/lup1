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
package fr.da2i.lup1.resource.security;

import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.security.AuthenticationService;
import fr.da2i.lup1.security.JwtManager;
import fr.da2i.lup1.security.Passwords;
import fr.da2i.lup1.util.AbstractRestlet;

@Path("authentication")
public class AuthenticationResource extends AbstractRestlet<String, Credential> {
	
	@Inject
	private JwtManager jwtManager;

	public AuthenticationResource() {
		super(Credential.class);
	}
	
	@Override
	@POST
	@Consumes("application/json")
	public Response create(Credential entity) throws SQLException {
		String id = entity.getId();
		if (dao.idExists(id)) {
			Credential fromDb = dao.queryForId(id);
			if (Passwords.check(entity.getPassword(), fromDb.getPassword())) {
				Map<String, Object> map = new HashMap<>();
				map.put("roles", new String[] { "default" });
				String jwt = jwtManager.build(id, map);
				URI instanceURI = uriInfo.getAbsolutePathBuilder().path(id).build();
				return Response.created(instanceURI).header(AuthenticationService.HEADER_KEY, jwt).build();
			}
		}
		throw new NotFoundException();
	}
	
	@Override
	@GET
	@Path("{id}")
	@Authenticated
	public Response get(@PathParam("id") String id) throws SQLException {
		return Response.ok().build();
	}

	@Override
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public Response update(@PathParam("id") String id, Credential entity) throws SQLException {
		throw new NotAllowedException(Response.noContent().build());
	}
	
	@Override
	@DELETE
	@Path("{id}")
	@Authenticated
	public Response delete(@PathParam("id") String id) throws SQLException {
		if (getAuthenticatedLogin().equals(id)) {
			return Response.noContent().build();
		}
		throw new NotFoundException();
	}

}
