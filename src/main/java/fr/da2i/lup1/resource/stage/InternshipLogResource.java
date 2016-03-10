package fr.da2i.lup1.resource.stage;

import java.net.URI;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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

import com.google.common.util.concurrent.Service.State;
import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.InternshipLog;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.resource.note.AnnualResource;
import fr.da2i.lup1.util.DaoProvider;

//@Authenticated
@PromotionAccess
public class InternshipLogResource extends AnnualResource{
	
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
	 * 
	 * @throws SQLException 
	 */
	@GET
	@Produces("application/json")
//	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response getLogs(@PathParam("etudiantId") int id) throws SQLException {
		List<InternshipLog> logs = dao.queryBuilder().where().eq("flag_id", 1).and().eq("member_id", id).query();
		if (logs.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(logs).build();
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
	 * 
	 * @throws SQLException 
	 */
	@GET
	@Produces("application/json")
	@Path("{stageId: [0-9]+}")
//	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response getLogsFromInternship(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) throws SQLException {
		List<InternshipLog> logs = dao.queryBuilder().where().eq("member_id", etudiantId).and().eq("internship_id", stageId).query();
		if (logs.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(logs).build();
	}
	
	/**
	 * Modifie les informations d'un log d'une offre pour un étudiant ou créer la lisaison si elle n'existe pas déjà
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @param 	stageId
	 * 				L'ID de l'offre de stage recherchée
	 * 
	 * @param	log
	 * 				Le log à modifier
	 * 
	 * @return	Le code de retour de la modification / création
	 * 
	 * @throws SQLException 
	 */
	@PUT
	@Produces("application/json")
	@Path("{stageId: [0-9]+}")
//	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response put(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId, InternshipLog log) throws SQLException {
		if (log.getFlagId() == null || log.getFlagId().getId() == 0 || log.getMemberId() == 0 || log.getInternshipId() == null || log.getInternshipId().getId() == 0 || log.getId() == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<InternshipLog> logs = dao.queryBuilder().where().eq("internship_log_id", log.getId()).and().eq("internship_id", stageId).and().eq("member_id", etudiantId).query();
		if (logs.isEmpty()) {
			return writeLog(etudiantId, stageId, log);
		}
		
		dao.update(log);
		
		return Response.status(Status.NO_CONTENT).build();	
	}
	
	/**
	 * Inscription d'un étudiant à une offre de stage
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @return	Le code de retour de l'inscription
	 * 
	 * @throws SQLException 
	 */
	@POST
	@Produces("application/json")
//	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response subscribeToInternship(@PathParam("etudiantId") int etudiantId, InternshipLog log) throws SQLException {
		List<InternshipLog> logs = dao.queryBuilder().where().eq("member_id", etudiantId).and().eq("internship_id", log.getInternshipId().getId()).query();
		if (logs.isEmpty()) {
			log.setMemberId(etudiantId);
			log.setDateLog(new Timestamp(new Date().getTime()));
			
			if (log.getFlagId() == null || log.getFlagId().getId() == 0 || log.getMemberId() == 0 || log.getInternshipId() == null || log.getInternshipId().getId() == 0) {
				return Response.status(Status.BAD_REQUEST).build();
			}
				
			dao.create(log);
			URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(log.getInternshipId().getId())).build();
			return Response.created(uri).build();
		}
		return Response.status(Status.CONFLICT).build();		
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
	 * @param	log
	 * 				Le log à ajouter
	 * 
	 * @return	Le code de retour de la création du log
	 * 
	 * @throws SQLException 
	 */
	@POST
	@Path("{stageId: [0-9]+}")
//	@RolesAllowed({ "etudiant", "responsable_formation", "responsable_stage" })
	public Response writeLog(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId, InternshipLog log) throws SQLException {
		log.setMemberId(etudiantId);
		log.setDateLog(new Timestamp(new Date().getTime()));
		
		if (log.getFlagId() == null || log.getFlagId().getId() == 0 || log.getMemberId() == 0 || log.getInternshipId() == null || log.getInternshipId().getId() == 0 || log.getId() == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		dao.create(log);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	/**
	 * Supprime toutes les inscription d'un membre à des offres de stage
	 * 
	 * @param 	etudiantId
	 * 				L'ID de l'étudiant recherché
	 * 
	 * @return	Le code de retour de la suppression, <NoContent> si ça s'est bien passé
	 * 
	 * @throws SQLException 
	 */
	@DELETE
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteSusbrictions(@PathParam("etudiantId") int etudiantId) throws SQLException {
		List<InternshipLog> logs = dao.queryBuilder().where().eq("member_id", etudiantId).query();
		if (logs.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		dao.delete(logs);
		return Response.status(Status.NO_CONTENT).build();
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
	 * @throws SQLException 
	 */
	@DELETE
	@Path("{stageId: [0-9]+}")
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteSusbriction(@PathParam("etudiantId") int etudiantId, @PathParam("stageId") int stageId) throws SQLException {
		List<InternshipLog> logs = dao.queryBuilder().where().eq("member_id", etudiantId).and().eq("internship_id", stageId).query();
		if (logs.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		dao.delete(logs);
		return Response.status(Status.NO_CONTENT).build();
	}
}
