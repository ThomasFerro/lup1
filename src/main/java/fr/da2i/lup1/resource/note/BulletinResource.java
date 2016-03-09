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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Promotion;
import fr.da2i.lup1.entity.formation.Register;
import fr.da2i.lup1.entity.note.Mark;
import fr.da2i.lup1.entity.note.Subject;
import fr.da2i.lup1.entity.note.Ue;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.filter.SemesterAccess;
import fr.da2i.lup1.resource.formation.AnnualResource;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;

@PromotionAccess
@SemesterAccess
@Authenticated
public class BulletinResource extends AnnualResource {
	
	private Dao<Promotion, Integer> promoDao;
	private Dao<Credential, String> credentialDao;
	private Dao<Mark, Integer> markDao;
	private Dao<Register, Integer> registerDao;
	
	@PathParam("semestre")
	private int semester;
	
	public BulletinResource() {
		this.promoDao = DaoProvider.getDao(Promotion.class);
		this.markDao = DaoProvider.getDao(Mark.class);
		this.registerDao = DaoProvider.getDao(Register.class);
		this.credentialDao = DaoProvider.getDao(Credential.class);
	}
	
	private List<Ue> getBulletin(Integer studentId, Integer semester) throws SQLException {
		List<Ue> bulletin = new ArrayList<>();
		List<Mark> marks = findFromPromotion(markDao.queryBuilder()).and().eq("student_id", studentId).and().eq("semester", semester).query();
		Ue ue;
		Subject sub;
		int i;
		for (Mark mark : marks) {
			ue = mark.getUe();
			i = bulletin.indexOf(ue);
			sub = mark.getSubject();
			sub.add(mark);
			if (i < 0) {
				ue.add(sub);
				bulletin.add(ue);
			}
			else {
				bulletin.get(i).add(sub);
			}
			ue.setCoeff(mark.getCoeffUe());
			sub.setCoeff(mark.getCoeffSubject());
		}
		return bulletin;
	}
	
	@GET
	@Produces("application/json")
	@RolesAllowed("responsable_formation")
	public Response list() throws SQLException {
		Integer responsableId = getCredential().getMember().getId();
		if (findFromPromotion(promoDao.queryBuilder()).and().eq("responsable_id", responsableId).countOf() == 0) {
			throw new ForbiddenException();
		}
		else {
			List<Register> registers = findFromPromotion(registerDao.queryBuilder()).query();
			Map<String, List<Ue>> bulletins = new HashMap<>();
			Credential credential;
			Integer studentId;
			for (Register register : registers) {
				studentId = register.getStudent().getId();
				credential = credentialDao.queryBuilder().where().eq("member_id", studentId).queryForFirst();
				bulletins.put(credential.getLogin(), getBulletin(studentId, semester));
			}
			return Response.ok(bulletins).build();
		}
	}
	
	@GET
	@Path("{user}")
	@Produces("application/json")
	@RolesAllowed({ "etudiant", "responsable_formation" })
	public Response get(@PathParam("user") String user) throws SQLException {
		Credential credential = getCredential();
		String login = credential.getLogin();
		int memberId = credential.getMember().getId();
		if (user.equals(login) || findFromPromotion(promoDao.queryBuilder()).and().eq("responsable_id", memberId).countOf() > 0) {
			String studentLogin;
			Integer studentId;
			if (user.equals(login)) {
				studentLogin = login;
				studentId = memberId;
			}
			else {
				Credential auth = credentialDao.queryBuilder().where().eq("login", user).queryForFirst();
				if (auth == null) {
					throw new NotFoundException();
				}
				studentLogin = auth.getLogin();
				studentId = auth.getMember().getId();
			}
			if (findFromPromotion(registerDao.queryBuilder()).and().eq("student_id", studentId).countOf() == 0) {
				throw new ForbiddenException();
			}
			else {
				Map<String, List<Ue>> bulletin = new HashMap<>();
				bulletin.put(studentLogin, getBulletin(studentId, semester));
				return Response.ok(bulletin).build();
			}
		}
		throw new ForbiddenException();
	}

}
