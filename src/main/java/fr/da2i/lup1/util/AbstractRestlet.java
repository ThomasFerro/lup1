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

import java.io.Serializable;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.j256.ormlite.dao.Dao;

public abstract class AbstractRestlet<ID extends Serializable, T extends Identifiable<ID>> implements Restlet<ID, T> {
	
	@Context
	protected UriInfo uriInfo;
	
	@Context
	protected SecurityContext securityContext;
	
	protected Dao<T,ID> dao;
	
	public AbstractRestlet(Class<T> clazz) {
		this.dao = DaoProvider.getDao(clazz);
	}
	
	protected String getAuthenticatedLogin() {
		Principal principal = securityContext.getUserPrincipal();
		if (principal == null) {
			return "";
		}
		return principal.getName();
	}
	
	@Override
	@POST
	@Consumes("application/json")
	public Response create(T entity) throws SQLException {
		if (dao.idExists(entity.getId())) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		dao.create(entity);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path(entity.getId().toString()).build();
		return Response.created(instanceURI).build();
	}

	@Override
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response get(@PathParam("id") ID id) throws SQLException {
		if (dao.idExists(id)) {
			return Response.ok(dao.queryForId(id)).build();
		}
		throw new NotFoundException();
	}

	@Override
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public Response update(@PathParam("id") ID id, T entity) throws SQLException {
		if (dao.idExists(id)) {
			throw new NotFoundException();
		}
		entity.setId(id);
		dao.update(entity);
		return Response.noContent().build();
	}

	@Override
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") ID id) throws SQLException {
		if (dao.idExists(id)) {
			dao.deleteById(id);
			return Response.noContent().build();
		}
		throw new NotFoundException();
	}

}
