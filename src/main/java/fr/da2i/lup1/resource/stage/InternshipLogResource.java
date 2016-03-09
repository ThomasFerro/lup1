package fr.da2i.lup1.resource.stage;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.InternshipLog;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.util.DaoProvider;

//@Authenticated
@PromotionAccess
public class InternshipLogResource {
	
	private Dao<InternshipLog, Integer> dao;
	
	/**
	 * Initialise le DAO en utilisant le provider
	 */
	public InternshipLogResource() {
		dao = DaoProvider.getDao(InternshipLog.class);
	}
	
	/**
	 * Retourne toutes les offres de stage dans lesquelles l'étudiant est inscrit
	 * 
	 * @param etudiantId
	 * 			L'ID de l'étudiant recherché
	 * 
	 * @return	Les offres de l'étudiant
	 */
	@GET
	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response getLogs(@PathParam("etudiantId") int id) {
		return Response.ok("Les offres de " + id).build();
	}
	
	/**
	 * Retourne les log de l'étudiant pour une offre de stage 
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @param 	stageId
	 * 				L'ID de l'offre de stage recherchée
	 * 
	 * @return	Les logs de l'étudiant pour l'offre de stage
	 */
	@GET
	@Path("{stageId: [0-9]+}")
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response getLogsFromInternship(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) {
		return Response.ok("Logs de l'offre " + stageId + " de " + etudiantId).build();
	}
	
	/**
	 * Modifie les informations et logs d'une offre pour un étudiant ou créer la lisaison si elle n'existe pas déjà
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @param 	stageId
	 * 				L'ID de l'offre de stage recherchée
	 * 
	 * @return	Le code de retour de la modification / création
	 */
	@PUT
	@Path("{stageId: [0-9]+}")
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response put(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) {
		return Response.ok("Remplace les informations de l'offre " + stageId + " pour le membre " + etudiantId + " ou la créer si elle n'existe pas").build();
	}
	
	/**
	 * Inscription d'un étudiant à une offre de stage
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @return	Le code de retour de l'inscription
	 */
	@POST
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response subscribeToInternship(@PathParam("etudiantId") int etudiantId) {
		return Response.ok("Inscription de l'étudiant " + etudiantId + " a une offre de stage").build();
	}
	
	/**
	 * Inscrit un nouveau log pour un membre dans une offre
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @param 	stageId
	 * 				L'ID de l'offre de stage recherchée
	 * 
	 * @return	Le code de retour de la création du log
	 */
	@POST
	@Path("{stageId: [0-9]+}")
	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response writeLog(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) {
		return Response.ok("Ajout d'un nouveau log par l'étudiant " + etudiantId + " pour l'offre " + stageId).build();
	}
	
	/**
	 * Supprime toutes les inscription d'un membre à des offres de stage
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @return	Le code de retour de la suppression, <NoContent> si ça s'est bien passé
	 */
	@DELETE
	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteSusbrictions(@PathParam("etudiantId") int etudiantId) {
		return Response.ok("Suppression des inscriptions aux offres de stage de l'étudiant " + etudiantId).build();
	}
	
	/**
	 * Supprime l'inscription d'un membre à une offre de stage
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @param 	stageId
	 * 				L'ID de l'offre de stage recherchée
	 * 
	 * @return	Le code de retour de la suppression, <NoContent> si ça s'est bien passé
	 */
	@DELETE
	@Path("{stageId: [0-9]+}")
	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteSusbriction(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) {
		return Response.ok("Suppression de l'inscription à l'offre de stage " + stageId + " de l'étudiant " + etudiantId).build();
	}
}
