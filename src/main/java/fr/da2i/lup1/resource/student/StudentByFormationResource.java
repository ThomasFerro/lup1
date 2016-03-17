package fr.da2i.lup1.resource.student;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.j256.ormlite.stmt.DeleteBuilder;

import fr.da2i.lup1.entity.formation.Formation;
import fr.da2i.lup1.entity.formation.Member;
import fr.da2i.lup1.entity.formation.Register;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.resource.formation.AnnualResource;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;

@Authenticated
@PromotionAccess
public class StudentByFormationResource extends AnnualResource {

	private Dao<Member, Integer> daoMember;
	private Dao<Register, Integer> daoRegister;
	private Dao<Credential, String> daoCredential;
	
	public StudentByFormationResource() {
		daoMember = DaoProvider.getDao(Member.class);
		daoRegister = DaoProvider.getDao(Register.class);
		daoCredential = DaoProvider.getDao(Credential.class);
	}
	
	/**
	 * Retourne la liste des étudiants de la promotion
	 * 
	 * @return	La liste des étudiants de cette promotion ou un code d'erreur <404> si la promotion n'existe pas ou si aucun étudiant n'y est inscrit
	 *
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed({ "responsable_formation", "etudiant" })
	@Produces("application/json")
	public Response getStudents() throws SQLException {
		List<Member> students = studentsInPromotion();
		
		if (students.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(students).build();
	}
	
	/**
	 * Retourne les informations de l'étudiant correspondant à l'ID passé en paramètre
	 * 
	 * @param	studentId
	 * 				L'ID de l'étudiant à rechercher
	 * 
	 * @return	Les informations de cet étudiant ou un code d'erreur <404> si il n'existe pas ou n'est pas étudiant dans cette promotion
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@RolesAllowed({ "responsable_formation", "etudiant" })
	@Produces("application/json")
	@Path("{studentId: [0-9]+}")
	public Response getStudent(@PathParam("studentId") int studentId) throws SQLException {
		Member student = studentInPromotion(studentId);
		
		if (student == null) {
			return Response.status(Status.NOT_FOUND).build(); 
		}
		
		return Response.ok(student).build();
	}
	
	/**
	 * Remplace les informations de l'étudiant correspondant à l'ID passé en paramètre dans cette promotion ou l'y ajouter si il n'existe pas
	 * 
	 * @param	studentId
	 * 				L'ID de l'étudiant à modifier / ajouter à la promotion
	 * 
	 * @return	Le code de retour correspondant au résultat de la requête
	 */
	@PUT
	@RolesAllowed("responsable_formation")
	@Path("{studentId: [0-9]+}")
	public Response putStudent(@PathParam("studentId") int studentId) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * Ajoute un étudiant à la promotion actuelle
	 * 
	 * @param	student
	 * 				L'étudiant à ajouter
	 * 
	 * @return	Le code de retour correspondant au résultat de la requête d'ajout à la promotion
	 * 
	 * @throws	SQLException 
	 */
	@POST
	@RolesAllowed("responsable_formation")
	public Response postStudent(Member student) throws SQLException {
		
		if (student(student.getId()) == null) {
			return Response.status(Status.NOT_FOUND).build(); 
		}
		if (studentInPromotion(student.getId()) != null) {
			return Response.status(Status.CONFLICT).build(); 
		}
		
		Register r = new Register(student, new Formation(formationId, ""), annee);
		
		daoRegister.create(r);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(student.getId())).build();
		
		return Response.created(uri).build();
	}
	
	/**
	 * Retire tous les étudiants de cette promotion
	 * 
	 * @return	Le code de retour des suppressions
	 * 
	 * @throws	SQLException 
	 */
	@DELETE
	@RolesAllowed("responsable_formation")
	public Response deleteStudents() throws SQLException {
		List<Register> registers = daoRegister.queryBuilder().where().eq("formation_id", formationId).and().eq("year", annee).query();
		
		if (registers.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build(); 
		}
		
		DeleteBuilder<Register, Integer> delete = daoRegister.deleteBuilder();
		delete.where().eq("formation_id", formationId).and().eq("year", annee);
		delete.delete();
		
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	/**
	 * Retire un étudiant de la promotion
	 * 
	 * @param	studentId
	 * 				L'ID de l'étudiant à retirer
	 * 
	 * @return	Le code de retour de la suppression
	 * 
	 * @throws	SQLException 
	 */
	@DELETE
	@RolesAllowed("responsable_formation")
	@Path("{studentId: [0-9]+}")
	public Response deleteStudent(@PathParam("studentId") int studentId) throws SQLException {
		List<Register> registers = daoRegister.queryBuilder().where().eq("formation_id", formationId).and().eq("year", annee).query();
		
		if (registers.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build(); 
		}
		
		DeleteBuilder<Register, Integer> delete = daoRegister.deleteBuilder();
		delete.where().eq("formation_id", formationId).and().eq("year", annee).and().eq("student_id", studentId);
		delete.delete();
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	private List<Member> studentsInPromotion() throws SQLException {
		List<Credential> creds = daoCredential.queryForAll();
		List<Member> m = new ArrayList<Member>();
		List<Register> r = null;
		for (Credential c : creds) {
			if (c.getRoles().contains("etudiant")) {
				r = daoRegister.queryBuilder().where().eq("student_id", c.getMember().getId()).and().eq("formation_id", formationId).and().eq("year", annee).query();
				if (!r.isEmpty()) {
					m.add(c.getMember());
				}
			}
		}
		return m;
	}
	
	private Member studentInPromotion(int id) throws SQLException {
		List<Credential> creds = daoCredential.queryBuilder().where().eq("member_id", id).query();
		List<Register> r = null;
		for (Credential c : creds) {
			if (c.getRoles().contains("etudiant")) {
				r = daoRegister.queryBuilder().where().eq("student_id", c.getMember().getId()).and().eq("formation_id", formationId).and().eq("year", annee).query();
				if (! r.isEmpty()) {
					return c.getMember();
				}
			}
		}
		return null;
	}
	
	private Member student(int id) throws SQLException {
		List<Credential> creds = daoCredential.queryBuilder().where().eq("member_id", id).query();
		for (Credential c : creds) {
			if (c.getRoles().contains("etudiant")) {
				return c.getMember();
			}
		}
		return null;
	}
}
