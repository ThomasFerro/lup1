package fr.da2i.lup1.resource.formation;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.model.Resource;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Formation;
import fr.da2i.lup1.entity.formation.Promotion;
import fr.da2i.lup1.entity.formation.Register;
import fr.da2i.lup1.security.Authenticated;
import fr.da2i.lup1.util.DaoProvider;
import fr.da2i.lup1.util.SimpleResource;

@Path("formations")
@Authenticated
public class FormationResource extends SimpleResource {

	private Dao<Formation, Integer> formationDao;
	private Dao<Promotion, Integer> promoDao;
	private Dao<Register, Integer> registerDao;

	public FormationResource() {
		this.formationDao = DaoProvider.getDao(Formation.class);
		this.promoDao = DaoProvider.getDao(Promotion.class);
		this.registerDao = DaoProvider.getDao(Register.class);
	}

	@GET
	@Produces("application/json")
	@RolesAllowed("admin")
	public Response list() throws SQLException {
		return Response.ok(formationDao.queryForAll()).build();
	}

	@GET
	@Path("{formationId: [0-9]+}")
	@Produces("application/json")
	@RolesAllowed({ "responsable_formation", "etudiant" })
	public Response get(@PathParam("formationId") Integer formationId) throws SQLException {
		if (promoDao.queryBuilder().where().eq("formation_id", formationId).and().eq("responsable_id", getMemberId()).countOf() == 0
					&& registerDao.queryBuilder().where().eq("formation_id", formationId).and().eq("student_id", getMemberId()).countOf() == 0) {
			throw new ForbiddenException();
		}
		if (formationDao.idExists(formationId)) {
			return Response.ok(formationDao.queryForId(formationId)).build();
		}
		throw new NotFoundException();
	}

	@Path("{formationId: [0-9]+}/annees/{annee: [0-9]{4}-[0-9]{4}}")
	public Resource getPromotionResource() {
		return Resource.from(PromotionResource.class);
	}

}
