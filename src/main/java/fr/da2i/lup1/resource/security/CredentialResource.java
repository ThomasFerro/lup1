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

import java.sql.SQLException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.da2i.lup1.entity.Credential;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.AbstractRestlet;

@Path("credentials")
public class CredentialResource extends AbstractRestlet<String, Credential> {

	public CredentialResource() {
		super(Credential.class);
	}
	
	@Override
	@POST
	@Consumes("application/json")
	public Response create(Credential entity) throws SQLException {
		entity.encrypt();
		if (entity.getPassword().length() == 0) {
			throw new BadRequestException();
		}
		return super.create(entity);
	}
	
	@Override
	@GET
	@Path("{id}")
	@Produces("application/json")
	@Authenticated
	public Response get(@PathParam("id") String id) throws SQLException {
		if (getAuthenticatedLogin().equals(id)) {
			return super.get(id);
		}
		throw new ForbiddenException();
	}
	
	@Override
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	@Authenticated
	public Response update(@PathParam("id") String id, Credential entity) throws SQLException {
		if (getAuthenticatedLogin().equals(id)) {
			entity.encrypt();
			if (entity.getPassword().length() == 0) {
				throw new BadRequestException();
			}
			return super.update(id, entity);
		}
		throw new ForbiddenException();
	}
	
	@Override
	@DELETE
	@Path("{id}")
	@Authenticated
	public Response delete(@PathParam("id") String id) throws SQLException {
		if (getAuthenticatedLogin().equals(id)) {
			return super.delete(id);
		}
		throw new ForbiddenException();
	}

}
