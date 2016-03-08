package fr.da2i.lup1.resource.stage;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.Internship;
import fr.da2i.lup1.resource.note.AnnualResource;
import fr.da2i.lup1.util.DaoProvider;

public class InternshipOfferResource extends AnnualResource {
	
	Dao<Internship, Integer> dao;
	
	/**
	 * Retourne la liste des offres de stage
	 * 
	 * @return	Un tableau d'objets JSON contenant les offres de stage
	 * 
	 * @throws SQLException 
	 */
	@GET
	@Produces("application/json")
	public List<Internship> get() throws SQLException{
		return getDao().queryForAll();
	}
	
	/**
	 * Retourne les informations d'une offre de stage
	 * 
	 * @param 	internshipId	
	 * 				L'ID de l'offre
	 * 
	 * @return	L'objet JSON de l'offre de stage
	 * 
	 * @throws SQLException 
	 */
	@GET
	@Path("{internshipId: [0-9]+}")
	@Produces("application/json")
	public Internship get(@PathParam("internshipId") Integer internshipId) throws SQLException {
		return getDao().queryBuilder().where().eq("internship_id", internshipId).query().get(0);
	}
	
	private Dao<Internship, Integer> getDao() {
		if(dao == null) {
			this.dao = DaoProvider.getDao(Internship.class);
		}
		return this.dao;
	}
}
