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

import java.sql.SQLException;

import javax.ws.rs.PathParam;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import fr.da2i.lup1.util.SimpleResource;

public abstract class AnnualResource extends SimpleResource {
	
	@PathParam("formationId")
	protected Integer formationId;
	@PathParam("annee")
	protected String annee;
	
	public Integer getFormationId() {
		return formationId;
	}
	
	public String getAnnee() {
		return annee;
	}
	
	public <T,ID> Where<T,ID> findFromPromotion(QueryBuilder<T, ID> queryBuilder) throws SQLException {
		return queryBuilder.where().eq("formation_id", formationId).and().eq("year", annee);
	}
	
	public <T,ID> Where<T,ID> findFromPromotion(Where<T,ID> where) throws SQLException {
		return where.eq("formation_id", formationId).and().eq("year", annee);
	}

}
