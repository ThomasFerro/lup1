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
package fr.da2i.lup1.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.note.UePromotion;
import fr.da2i.lup1.util.DaoProvider;

@Priority(Priorities.USER)
@SemesterAccess
public class SemesterFilter implements ContainerRequestFilter {
	
	private Dao<UePromotion, Integer> uePromoDao;
	
	public SemesterFilter() {
		this.uePromoDao = DaoProvider.getDao(UePromotion.class);
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		UriInfo uriInfo = requestContext.getUriInfo();
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String formationId = params.getFirst("formationId");
		String year = params.getFirst("annee");
		String semester = params.getFirst("semestre");
		try {
			if (uePromoDao.queryBuilder().where().eq("formation_id", formationId).and().eq("year", year).and().eq("semester", semester).countOf() == 0) {
				requestContext.abortWith(Response.status(Response.Status.NOT_FOUND).build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			requestContext.abortWith(Response.serverError().build());
		}
	}

}
