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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.da2i.lup1.security.Authenticated;

public class BulletinResource extends AnnualResource {
	
	@GET
	@Path("{bulletinId: [0-9]+}")
	@Authenticated
	public String get(@PathParam("bulletin") Integer bulletinId) {
		return "Bulletin" + bulletinId + " de la formation " + formationId + " (" + annee + ") avec le credential " + getCredential();
	}
	
	@GET
	@Path("{bulletinId: [0-9]+}")
	public String getBulletin(@PathParam("bulletinId") Integer bulletinId) {
		return "Bulletin " + bulletinId + " de la formation " + formationId + " (" + annee + ")";
	}

}
