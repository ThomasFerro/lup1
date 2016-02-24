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
import java.util.Collection;
import java.util.Map;

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
import javax.ws.rs.core.UriInfo;

public abstract class AbstractRestlet<ID extends Serializable, T> implements Restlet<ID, T> {
	
	@Context
	private UriInfo uriInfo;
	
	private Map<ID, T> dao;
	
	public AbstractRestlet(Class<? extends Dao<ID,T>> daoClass) {
		this.dao = DaoProvider.getDao(daoClass);
	}
	
	/**
	 * Génère un nouvel identifiant pour le prochain objet à insérer en base
	 * 
	 * @return	une instance de <ID>
	 */
	public abstract ID generate();

	@Override
	@POST
	@Consumes("application/json")
	public Response create(T entity) {
		if (dao.containsValue(entity)) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		ID newId = generate();
		dao.put(newId, entity);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path(newId.toString()).build();
		return Response.created(instanceURI).build();
	}
	
	@Override
	@GET
	@Produces("application/json")
	public Collection<T> list() {
		return dao.values();
	}

	@Override
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response get(@PathParam("id") ID id) {
		if (dao.containsKey(id)) {
			return Response.ok(dao.get(id)).build();
		}
		throw new NotFoundException();
	}

	@Override
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public Response update(@PathParam("id") ID id, T entity) {
		if (dao.containsKey(id)) {
			dao.put(id, entity);
			return Response.noContent().build();
		}
		throw new NotFoundException();
	}

	@Override
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") ID id) {
		if (dao.containsKey(id)) {
			dao.remove(id);
			return Response.noContent().build();
		}
		throw new NotFoundException();
	}

}
