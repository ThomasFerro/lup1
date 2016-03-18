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
package fr.da2i.lup1.resource.ue;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.note.Ue;
import fr.da2i.lup1.resource.formation.AnnualResource;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;

@Path("ues")
@Authenticated
public class UeResource extends AnnualResource {
	
	private Dao<Ue, Integer> dao;
	
	public UeResource() { 
		dao = DaoProvider.getDao(Ue.class);
	}
	
	/**
	 * Retourne la liste des UEs
	 * 
	 * @return	La liste des UEs dans la base
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed("responsable_formation")
	@Produces("application/json")
	public Response getUes() throws SQLException {
		List<Ue> ues = dao.queryForAll();
		
		if (ues.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(ues).build();
	}
	
	/**
	 * Retourne les informations d'une UE
	 * 
	 * @param	ueId
	 * 				L'ID de l'UE à rechercher
	 * 
	 * @return	Les informations de cette UE ou un code d'erreur <404> si elle n'existe pas
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed("responsable_formation")
	@Path("{ueId: [0-9]+}")
	@Produces("application/json")
	public Response getUe(@PathParam("ueId") int ueId) throws SQLException {
		List<Ue> ues = dao.queryBuilder().where().eq("ue_id", ueId).query();
		
		if (ues.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(ues).build();
	}
	
	/**
	 * Remplace les informations d'une UE ou l'ajoute si elle n'existe pas
	 * 
	 * @param	ueId
	 * 				L'ID de l'UE à modifier ou ajouter
	 * 
	 * @param	ue
	 * 				Les informations de l'UE
	 * 
	 * @return	Le code de retour de la modification ou de l'ajout
	 * 
	 * @throws	SQLException 
	 */
	@PUT
	@RolesAllowed("responsable_formation")
	@Path("{ueId: [0-9]+}")
	public Response putUe(@PathParam("ueId") int ueId, Ue ue) throws SQLException {
		Ue u = dao.queryBuilder().where().eq("ue_id", ueId).queryForFirst();
		
		if (u == null) {
			return createUe(ue);
		}
		
		if (Strings.isNullOrEmpty(u.getName())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		ue.setId(ueId);
		dao.update(ue);
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	/**
	 * Créer une nouvelle UE
	 * 
	 * @param	ue
	 * 				Les informations de l'UE à créer
	 * 
	 * @return	Le code de retour de l'ajout à la base
	 * 
	 * @throws	SQLException 
	 */
	@POST
	@RolesAllowed("responsable_formation")
	public Response postUe(Ue ue) throws SQLException {
		if (Strings.isNullOrEmpty(ue.getName())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return createUe(ue);
	}
	
	private Response createUe(Ue ue) throws SQLException {
		if (Strings.isNullOrEmpty(ue.getName())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		dao.create(ue);
	
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(ue.getId())).build();
		
		
		return Response.created(uri).build();
	}
}
