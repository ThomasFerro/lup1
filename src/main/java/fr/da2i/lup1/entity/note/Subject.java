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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "subject")
public class Subject extends Identifiable<Integer> implements Average {
	
	@DatabaseField(columnName = "subject_id", id = true)
	private Integer subjectId;
	@DatabaseField(columnName = "name")
	private String name;
	
	private double coeff;
	private List<Mark> marks = new ArrayList<>();
	
	public Subject() {}

	public Integer getId() {
		return subjectId;
	}

	public void setId(Integer subjectId) {
		this.subjectId = subjectId;
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
	
	public List<Mark> getMarks() {
		return marks;
	}
	
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	public boolean add(Mark mark) {
		return getMarks().add(mark);
	}
	
	public boolean remove(Mark mark) {
		return getMarks().remove(mark);
	}
	
	public Mark get(int idx) {
		return getMarks().get(idx);
	}
	
	public int indexOf(Mark mark) {
		return getMarks().indexOf(mark);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + ", coeff=" + getCoeff() + "]";
	}

	@Override
	@JsonProperty("avg")
	public double avg() {
		double avg = 0;
		double sum = 0;
		for (Mark mark : marks) {
			sum += mark.getCoeffEval();
			avg += (mark.getCoeffEval() * mark.getMark());
		}
		return avg / sum;
	}
	
}
