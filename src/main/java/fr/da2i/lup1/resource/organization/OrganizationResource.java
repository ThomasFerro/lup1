package fr.da2i.lup1.resource.organization;

import java.sql.SQLException;
import java.util.List;

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

import fr.da2i.lup1.entity.organization.Organization;
import fr.da2i.lup1.util.DaoProvider;

//@Authenticated
@Path("entreprises")
public class OrganizationResource {
	
	private Dao<Organization, String> dao;
	
	/**
	 * Initialise le DAO en utilisant le provider
	 */
	public OrganizationResource() {
		dao = DaoProvider.getDao(Organization.class);
	}
	
	/**
	 * Retourne toutes les entreprises de la base
	 * 
	 * @return	Les entreprises et leurs informations
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@Produces("application/json")
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response getOrganizations() throws SQLException {
		List<Organization> organizations = dao.queryForAll();
		if (organizations.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(organizations).build();
	}
	
	/**
	 * Retourne les informations d'une entreprise
	 * 
	 * @param	siret	
	 * 				Le numéro SIRET de l'entreprise à rechercher
	 * 
	 * @return	Les informations de l'entreprise ou un code d'erreur <404> si elle n'existe pas
	 * 
	 * @throws	SQLException 
	 */
	@GET
	@Produces("application/json")
	@Path("{siret: [0-9]{14}}")
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response getOrganization(@PathParam("siret") String siret) throws SQLException {
		List<Organization> organizations = dao.queryBuilder().where().eq("siret", siret).query();
		if (organizations.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(organizations.get(0)).build();
	}
	
	/**
	 * Modifie ou ajoute à la base les informations de l'entreprise
	 * 
	 * @param	siret
	 * 				Le numéro SIRET de l'entreprise à ajouter ou modifier
	 * 
	 * @param	organization
	 * 				Les informations de l'entreprise à ajouter ou modifier si elle existe déjà
	 * 
	 * @return	Le code HTTP de retour de la modification ou de l'insertion
	 * 
	 * @throws	SQLException 
	 */
	@PUT
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	@Path("{siret: [0-9]{14}}")
	public Response putOrganization(@PathParam("siret") String siret, Organization organization) throws SQLException {
		if (Strings.isNullOrEmpty(organization.getId())) {
			organization.setId(siret);
		}
		
		List<Organization> organizations = dao.queryBuilder().where().eq("siret", siret).query();
		if (organizations.isEmpty()) {
			return postOrganization(organization);
		}

		int ret = dao.update(organization); 
		
		if (ret > 0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else if (ret == 0) {
			return Response.status(Status.NOT_MODIFIED).build();
		}
		else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Créer une nouvelle entrée dans la table des entreprises
	 * 
	 * @param	organization
	 * 				Les informations de l'entreprise à ajouter dans la table
	 * 
	 * @return	Le code de retour de l'insertion dans la table
	 * 
	 * @throws	SQLException 
	 */
	@POST
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response postOrganization(Organization organization) throws SQLException {
		if (Strings.isNullOrEmpty(organization.getId()) || organization.getId().length() != 14) {
			return Response.status(Status.BAD_REQUEST).build();
		}		
		
		List<Organization> organizations = dao.queryBuilder().where().eq("siret", organization.getId()).query();
		
		if (organizations.isEmpty()) {
			dao.create(organization);
			return Response.status(Status.NO_CONTENT).build();
		}
		else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	/**
	 * Supprime toutes les entrées de la table des entreprises
	 * 
	 * @return	Le code de retour des suppressions
	 * 
	 * @throws	SQLException 
	 */
	@DELETE
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	public Response deleteOrganizations() throws SQLException {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * Supprime une entrée dans la table des entreprises
	 * 
	 * @param	siret
	 * 				Le numéro SIRET de l'entreprise à retirer de la base
	 * 
	 * @return	Le code de retour de la suppression
	 * 
	 * @throws SQLException 
	 */
	@DELETE
//	@RolesAllowed({ "responsable_formation", "responsable_stage" })
	@Path("{siret: [0-9]{14}}")
	public Response deleteOrganization(@PathParam("siret") String siret) throws SQLException {
//		List<Organization> organizations = dao.queryBuilder().where().eq("siret", siret).query();
//		
//		if  (organizations.isEmpty()) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		int ret = dao.delete(organizations.get(0));
//		return Response.ok(ret).build();

		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
}
