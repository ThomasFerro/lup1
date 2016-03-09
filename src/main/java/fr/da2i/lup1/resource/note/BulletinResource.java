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
package fr.da2i.lup1.resource.note;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Register;
import fr.da2i.lup1.entity.note.Mark;
import fr.da2i.lup1.entity.note.Subject;
import fr.da2i.lup1.entity.note.Ue;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;

@PromotionAccess
@Authenticated
public class BulletinResource extends AnnualResource {
	
	private Dao<Credential, String> credentialDao;
	private Dao<Mark, Integer> markDao;
	private Dao<Register, Integer> registerDao;
	
	public BulletinResource() {
		this.markDao = DaoProvider.getDao(Mark.class);
		this.registerDao = DaoProvider.getDao(Register.class);
		this.credentialDao = DaoProvider.getDao(Credential.class);
	}
	
	private Set<Ue> getBulletin(Integer studentId) throws SQLException {
		Set<Ue> bulletin = new HashSet<>();
		List<Mark> marks = findFromPromotion(markDao.queryBuilder()).and().eq("student_id", studentId).query();
		Ue ue;
		Subject sub;
		for (Mark mark : marks) {
			ue = mark.getUe();
			sub = mark.getSubject();
			sub.add(mark);
			ue.add(sub);
			bulletin.add(ue);
			
			ue.setCoeff(mark.getCoeffUe());
			sub.setCoeff(mark.getCoeffSubject());
		}
		return bulletin;
	}
	
	@GET
	@Produces("application/json")
	@RolesAllowed("responsable_formation")
	public Response list() throws SQLException {
		List<Register> registers = findFromPromotion(registerDao.queryBuilder()).query();
		Map<String, Set<Ue>> bulletins = new HashMap<>();
		Credential credential;
		Integer studentId;
		for (Register register : registers) {
			studentId = register.getStudent().getId();
			credential = credentialDao.queryBuilder().where().eq("member_id", studentId).queryForFirst();
			bulletins.put(credential.getLogin(), getBulletin(studentId));
		}
		return Response.ok(bulletins).build();
	}
	
	@GET
	@Path("{user}")
	@Produces("application/json")
	@RolesAllowed("etudiant")
	public Response get(@PathParam("user") String user) throws SQLException {
		String login = getCredential().getLogin();
		if (user.equals(login)) {
			int studentId = getCredential().getMember().getId();
			if (findFromPromotion(registerDao.queryBuilder()).and().eq("student_id", studentId).countOf() == 0) {
				return Response.status(Response.Status.FORBIDDEN).build();
			}
			else {
				Map<String, Set<Ue>> bulletin = new HashMap<>();
				bulletin.put(login, getBulletin(studentId));
				return Response.ok(bulletin).build();
			}
		}
		return Response.status(Response.Status.FORBIDDEN).build();
	}

}
