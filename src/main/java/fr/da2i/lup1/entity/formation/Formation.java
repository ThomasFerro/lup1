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

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "formation")
public class Formation extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "formation_id", id = true)
	private int formationId;
	@DatabaseField(columnName = "name")
	private String name;
	@ForeignCollectionField @JsonIgnore
	private ForeignCollection<Promotion> promotions;
	
	public Formation(int formationId, String name) {
		this.formationId = formationId;
		this.name = name;
	}
	
	public Formation() {}

	public Integer getId() {
		return formationId;
	}

	public void setId(Integer formationId) {
		this.formationId = formationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("promotions")
	public List<Promotion> getPromotions() {
		return Arrays.asList(promotions.toArray(new Promotion[promotions.size()]));
	}
	
	@Override
	public String toString() {
		return "Formation [formationId=" + formationId + ", name=" + name + ", promotions=" + getPromotions() + "]";
	}

}
