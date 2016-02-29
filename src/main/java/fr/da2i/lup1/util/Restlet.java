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
import java.sql.SQLException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Webservice en REST
 * 
 * @param	<ID>
 * 			le type de l'identifiant
 * @param	<T>
 * 			le type de l'objet associé
 */
public interface Restlet<ID extends Serializable, T extends Identifiable<ID>> {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response create(T entity) throws SQLException;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Collection<T> list() throws SQLException;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Response get(ID id) throws SQLException;
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(ID id, T entity) throws SQLException;
	
	@DELETE
	@Path("{id}")
	Response delete(ID id) throws SQLException;

}
