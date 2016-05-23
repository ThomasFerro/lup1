package fr.da2i.lup1.resource.student;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
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

import fr.da2i.lup1.entity.formation.Member;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.entity.security.Role;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;
import fr.da2i.lup1.util.SimpleResource;

/**
 * @author Thomas FERRO
 */
@Path("etudiants")
@Authenticated
public class StudentResource extends SimpleResource {

	private Dao<Member, Integer> dao;
	private Dao<Credential, String> daoCredential;
	private Dao<Role, String> daoRole;

	/**
	 * Initialise le DAO en utilisant le provider
	 */
	public StudentResource() {
		dao = DaoProvider.getDao(Member.class);
		daoCredential = DaoProvider.getDao(Credential.class);
		daoRole = DaoProvider.getDao(Role.class);
	}

	/**
	 * Retourne la liste des étudiants
	 *
	 * @return	La liste des étudiants de la base
	 *
	 * @throws	SQLException
	 */
	@GET
	@Produces("application/json")
	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response getStudents() throws SQLException {
		List<Member> students = students();
		if (students.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(students).build();
	}

	/**
	 * Retourne les informations d'un étudiant
	 *
	 * @param	studentId
	 * 				L'ID de l'étudiant à rechercher
	 *
	 * @return	Les informations de l'étudiant ou un code <404> si il n'existe pas
	 *
	 * @throws	SQLException
	 */
	@GET
	@Produces("application/json")
	@Path("{studentId: [0-9]+}")
	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response getStudent(@PathParam("studentId") int studentId) throws SQLException {
		Member m = student(studentId);
		if (m == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(m).build();
	}

	/**
	 * Remplace les informations d'un étudiant ou l'ajoute si il n'existe pas
	 *
	 * @param	studentId
	 * 				L'ID de l'étudiant à modifier ou ajouter
	 *
	 * @param	student
	 * 				Les informations sur l'étudiant, mappé sur l'objet Member
	 *
	 * @return	Le code de retour de la modification ou de l'ajout à la base
	 *
	 * @throws	SQLException
	 */
	@PUT
	@Path("{studentId: [0-9]+}")
	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response putStudent(@PathParam("studentId") int studentId, Member student) throws SQLException {
		Member m = student(studentId);
		if (m != null) {
			if (Strings.isNullOrEmpty(student.getLastName()) || Strings.isNullOrEmpty(student.getFirstName())) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			student.setId(studentId);
			dao.update(student);
			return Response.status(Status.NO_CONTENT).build();
		}
		else {
			return addStudent(student, false);
		}
	}

	/**
	 * Créer un nouvel étudiant
	 *
	 * @param	student
	 * 				L'étudiant à créer
	 *
	 * @return	Le code de retour de l'ajout à la base
	 *
	 * @throws	SQLException
	 */
	@POST
	@RolesAllowed({ "responsable_formation", "responsable_stage", "etudiant" })
	public Response postStudent(Member student) throws SQLException {
		return addStudent(student, true);
	}

	private Response addStudent(Member student, boolean post) throws SQLException {
		if (Strings.isNullOrEmpty(student.getLastName()) || Strings.isNullOrEmpty(student.getFirstName())) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		String login;

		if (student.getLastName().length() < 7) {
			login = student.getLastName().substring(0, student.getLastName().length()).toLowerCase() + student.getFirstName().substring(0, 1).toLowerCase();
		}
		else {
			login = student.getLastName().substring(0, 7).toLowerCase() + student.getFirstName().substring(0, 1).toLowerCase();
		}

		List<Role> r = daoRole.queryBuilder().where().eq("login", login).and().eq("role", "etudiant").query();

		if (!r.isEmpty() || daoCredential.idExists(login)) {
			return Response.status(Status.CONFLICT).build();
		}

		dao.create(student);

		Credential cred = new Credential(login, login, student);
		daoCredential.createIfNotExists(cred);

		Role role = new Role(cred, "etudiant");
		daoRole.create(role);

		URI uri;

		if (post) {
			uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(student.getId())).build();
		}
		else {
			uri = uriInfo.getAbsolutePathBuilder().build();
		}

		return Response.created(uri).build();
	}

	private List<Member> students() throws SQLException {
		List<Credential> creds = daoCredential.queryForAll();
		List<Member> m = new ArrayList<Member>();
		for (Credential c : creds) {
			if (c.getRoles().contains("etudiant")) {
				m.add(c.getMember());
			}
		}
		return m;
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
