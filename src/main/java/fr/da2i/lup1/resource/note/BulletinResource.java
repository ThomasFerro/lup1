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
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Member;
import fr.da2i.lup1.entity.formation.Promotion;
import fr.da2i.lup1.entity.formation.Register;
import fr.da2i.lup1.entity.note.Bulletin;
import fr.da2i.lup1.entity.note.Mark;
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
	
	private Bulletin getBulletin(Member student, Integer semester) throws SQLException {
		Bulletin bulletin = new Bulletin();
		List<Mark> marks = findFromPromotion(markDao.queryBuilder()).and().eq("student_id", student.getId()).and().eq("semester", semester).query();
		for (Mark mark : marks) {
			bulletin.add(mark);
		}
		bulletin.setStudent(student);
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
			List<Bulletin> bulletins = new ArrayList<>();
			for (Register register : registers) {
				bulletins.add(getBulletin(register.getStudent(), semester));
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
		Member member = credential.getMember();
		if (user.equals(login) || findFromPromotion(promoDao.queryBuilder()).and().eq("responsable_id", member.getId()).countOf() > 0) {
			Member student = member;
			if (!user.equals(login)) {
				Credential auth = credentialDao.queryBuilder().where().eq("login", user).queryForFirst();
				if (auth == null) {
					throw new NotFoundException();
				}
				student = auth.getMember();
			}
			if (findFromPromotion(registerDao.queryBuilder()).and().eq("student_id", student.getId()).countOf() == 0) {
				throw new ForbiddenException();
			}
			else {
				List<Bulletin> bulletin = new ArrayList<>();
				bulletin.add(getBulletin(student, semester));
				return Response.ok(bulletin).build();
			}
		}
		throw new ForbiddenException();
	}

}
