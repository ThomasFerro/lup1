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
package fr.da2i.lup1.resource.formation;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.model.Resource;

import fr.da2i.lup1.filter.PromotionAccess;
import fr.da2i.lup1.filter.SemesterAccess;
import fr.da2i.lup1.resource.note.BulletinResource;
import fr.da2i.lup1.resource.note.SubjectResource;
import fr.da2i.lup1.resource.ue.UePromotionResource;
import fr.da2i.lup1.security.Authenticated;

@PromotionAccess
@SemesterAccess
@Authenticated
public class SemestreResource {
	
	@Path("bulletins")
	public Resource getBulletinResource() {
		return Resource.from(BulletinResource.class);
	}
	
	@Path("ues")
	public Resource getUeResource() {
		return Resource.from(UePromotionResource.class);
	}
	
	@Path("subjects")
	public Resource getSubjectResource() {
		return Resource.from(SubjectResource.class);
	}

}
