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
package fr.da2i.lup1.entity.note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "ue")
public class Ue extends Identifiable<Integer> implements Average {
	
	@DatabaseField(columnName = "ue_id", id = true)
	private Integer ueId;
	@DatabaseField(columnName = "name")
	private String name;
	
	private double coeff;
	private List<Subject> subjects = new ArrayList<>();
	
	public Ue(int ueId, String nameId) {
		this.ueId = ueId;
		this.name = nameId;
	}
	
	public Ue() {}

	public Integer getId() {
		return ueId;
	}

	public void setId(Integer ueId) {
		this.ueId = ueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getCoeff() {
		return coeff;
	}
	
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
	public boolean add(Subject subject) {
		return getSubjects().add(subject);
	}
	
	public boolean remove(Subject subject) {
		return getSubjects().remove(subject);
	}
	
	public Subject get(int idx) {
		return getSubjects().get(idx);
	}
	
	public int indexOf(Subject sub) {
		return getSubjects().indexOf(sub);
	}
	
	public boolean addAll(Collection<Subject> subjects) {
		return subjects.addAll(subjects);
	}

	@Override
	public String toString() {
		return "Ue [ueId=" + ueId + ", name=" + name + "]";
	}
	
	@Override
	@JsonProperty("avg")
	public double avg() {
		double avg = 0;
		double sum = 0;
		for (Subject sub : subjects) {
			sum += sub.getCoeff();
			avg += sub.avg() * sub.getCoeff();
		}
		return avg / sum;
	}
	
}
