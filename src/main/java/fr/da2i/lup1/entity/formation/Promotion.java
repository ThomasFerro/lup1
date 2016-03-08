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
package fr.da2i.lup1.entity.formation;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "promotion")
public class Promotion extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "promotion_id", id = true)
	private int promotionId;
	@DatabaseField(columnName = "year")
	private int year;
	@DatabaseField(columnName = "formation_id")
	private int formationId;
	@DatabaseField(columnName = "responsable_id")
	private int responsableId;
	
	public Promotion(int promotionId, int year, int formationId, int responsableId) {
		this.promotionId = promotionId;
		this.year = year;
		this.formationId = formationId;
		this.responsableId = responsableId;
	}

	public Integer getId() {
		return promotionId;
	}

	public void setId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFormationId() {
		return formationId;
	}

	public void setFormationId(int formationId) {
		this.formationId = formationId;
	}

	public int getResponsableId() {
		return responsableId;
	}

	public void setResponsableId(int responsableId) {
		this.responsableId = responsableId;
	}
}
