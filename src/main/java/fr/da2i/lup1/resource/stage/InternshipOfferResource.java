package fr.da2i.lup1.resource.stage;

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

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.Internship;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.resource.note.AnnualResource;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;


@Authenticated
@PromotionAccess
public class InternshipOfferResource extends AnnualResource {
	
	private Dao<Internship, Integer> dao;
	
	/**
	 * Initialise le DAO pour les requêtes vers la BDD
	 */
	public InternshipOfferResource() {
		dao = DaoProvider.getDao(Internship.class);
	}
	
	/**
	 * Retourne la liste des offres de stage
	 * 
	 * @return	Un tableau d'objets JSON contenant les offres de stage
	 * 
	 * @throws 	SQLException
	 */
	@GET
	@Produces("application/json")
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response getStages() throws SQLException {
		
		List<Internship> stages = findFromPromotion(dao.queryBuilder()).query();
		if (stages.isEmpty())
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(stages).build();
	}
	
	/**
	 * Retourne les informations d'une offre de stage
	 * 
	 * @param 	internshipId	
	 * 				L'ID de l'offre
	 * 
	 * @return	L'objet JSON de l'offre de stage ou une erreur <404>
	 * 
	 * @throws SQLException 
	 */
	@GET
	@Path("{internshipId: [0-9]+}")
	@Produces("application/json")
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response getStage(@PathParam("internshipId") Integer internshipId) throws SQLException {
		List<Internship> stage = findFromPromotion(dao.queryBuilder()).and().eq("internship_id", internshipId).query();
		if (stage.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(stage.get(0)).build();
	}
	
	/**
	 * Remplace les informations de cette offre ou la créer si elle n'existe pas
	 * 
	 * @param 	internshipId
	 * 				L'ID de l'offre à modifier ou créer
	 * 
	 * @return
	 * 			Le code de retour HTTP
	 * 
	 * @throws 	SQLException
	 */
	@PUT
	@Path("{internshipId: [0-9]+}")
	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response putStage(@PathParam("internshipId") Integer internshipId, Internship stage) throws SQLException {
		stage.setId(internshipId);
		if (Strings.isNullOrEmpty(stage.getSiret()) || Strings.isNullOrEmpty(stage.getYear()) || stage.getFormationId() == 0 || stage.getId() == 0) {
			return Response.status(Status.BAD_REQUEST).build(); 
		}
		if (dao.idExists(internshipId)) {
			dao.update(stage);
			return Response.status(Status.NO_CONTENT).build();		
		}
		return post(stage);
	}
	
	/**
	 * Créer une nouvelle offre de stage
	 * 
	 * @return	Le code de retour HTTP
	 * 
	 * @throws 	SQLException
	 */
	@POST
	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response post(Internship stage) throws SQLException {
		stage.setFormationId(formationId);
		stage.setYear(annee);
		if (Strings.isNullOrEmpty(stage.getSiret()) || Strings.isNullOrEmpty(stage.getYear()) || stage.getFormationId() == 0) {
			return Response.status(Status.BAD_REQUEST).build(); 
		}
		dao.create(stage);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(stage.getId())).build();
		return Response.created(uri).build();
	}
	
	/**
	 * Supprime l'offre de stage
	 * 
	 * @param 	internshipId
	 * 				L'ID de l'offre à supprimer
	 * 
	 * @return	Le code de retour HTTP
	 * 
	 * @throws 	SQLException
	 */
	@DELETE
	@Path("{internshipId: [0-9]+}")
	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteStage(@PathParam("internshipId") Integer internshipId) throws SQLException {
		Internship stage = new Internship();
		stage.setId(internshipId);
		if (dao.delete(stage) == 1) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
