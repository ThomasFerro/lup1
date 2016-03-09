package fr.da2i.lup1.resource.note;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.glassfish.jersey.server.model.Resource;

import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.resource.stage.InternshipLogResource;

//@Authenticated
@PromotionAccess
public class EtudiantResource {
	
	@GET
	@Path("{etudiantId: [0-9]+}")
	public String get(@PathParam("etudiantId") int etudiantId) {
		return "Etudiant " + etudiantId;
	}
	
	@Path("{etudiantId: [0-9]+}/stages")
	public Resource getStageLogResource() {
		return Resource.from(InternshipLogResource.class);
	}
}
