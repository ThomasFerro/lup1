package fr.da2i.lup1.resource.ue;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Formation;
import fr.da2i.lup1.entity.note.Ue;
import fr.da2i.lup1.entity.note.UePromotion;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.resource.formation.AnnualResource;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;

@Authenticated
@PromotionAccess
public class UePromotionResource extends AnnualResource {

	private Dao<UePromotion, Integer> dao;
	private Dao<Ue, Integer> daoUe;
	@PathParam("semestre")
	private int semester;
	
	public UePromotionResource() {
		dao = DaoProvider.getDao(UePromotion.class);
		daoUe = DaoProvider.getDao(Ue.class);
	}
	
	/**
	 * Retourne la liste des UE de la promotion pour un semestre
	 * 
	 * @return	La liste des UEs de la promotion
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed({ "responsable_formation", "etudiant" })
	@Produces("application/json")
	public Response getUesPromotion() throws SQLException {
		List<UePromotion> uesPromotion = dao.queryBuilder().where().eq("semester", this.semester).and().eq("formation_id", formationId).and().eq("year", annee).query();
		
		if (uesPromotion.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(uesPromotion).build();
	}
	
	/**
	 * Retourne les informations de l'UE
	 * 
	 * @param	ueId
	 * 				L'ID de l'UE à rechercher
	 * 
	 * @return	Les informations de cette UE
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed({ "responsable_formation", "etudiant" })
	@Produces("application/json")
	@Path("{ueId: [0-9]+}")
	public Response getUePromotion(@PathParam("ueId") int ueId) throws SQLException {
		UePromotion uePromotion = dao.queryBuilder().where().eq("semester", this.semester).and().eq("formation_id", formationId).and().eq("year", annee).and().eq("ue_id", ueId).queryForFirst();
		
		if (uePromotion == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(uePromotion).build();
	}
	
	/**
	 * Dépréciée
	 */
	@PUT
	@RolesAllowed("responsable_formation")
	@Path("{ueId: [0-9]+}")
	public Response putUe(@PathParam("ueId") int ueId) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * Ajouter une UE dans un semestre de la promotion
	 * 
	 * @param	ue
	 * 				L'UE à ajouter à la promotion
	 * 
	 * @return	Le code de retour de l'ajout à la base
	 * 
	 * @throws	SQLException 
	 */
	@POST
	@RolesAllowed("responsable_formation")
	public Response postUe(Ue ue) throws SQLException {
		if (ue.getId() == null || ue.getId() == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (daoUe.queryBuilder().where().eq("ue_id", ue.getId()).queryForFirst() == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		UePromotion uePromotion = dao.queryBuilder().where().eq("semester", this.semester).and().eq("formation_id", formationId).and().eq("year", annee).and().eq("ue_id", ue.getId()).queryForFirst();
		
		if (uePromotion != null) {
			return Response.status(Status.CONFLICT).build();
		}
		
		UePromotion u = new UePromotion(new Formation(formationId, ""), annee, this.semester, ue.getId());
		
		dao.create(u);

		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(ue.getId())).build();
		
		return Response.created(uri).build();
	}
	
	/**
	 * Retire toutes les UE de la promotion
	 * 
	 * @return	Le code de retour des suppressions
	 */
	@DELETE
	@RolesAllowed("responsable_formation")
	public Response deleteUes() {
		return Response.ok("Retire toutes les UE de la promotion").build();
	}
	
	/**
	 * Retire une UE de la promotion
	 * 
	 * @param	ueId
	 * 				L'UE à retirer
	 * 
	 * @return	Le code de retour de la suppression
	 */
	@DELETE
	@RolesAllowed("responsable_formation")
	@Path("{ueId: [0-9]+}")
	public Response deleteUes(@PathParam("ueId") int ueId) {
		return Response.ok("Retire l'UE à cette adresse de la promotion").build();
	}
}
