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

@DatabaseTable(tableName = "is_register_in")
public class Register {

	@DatabaseField(columnName = "student_id", foreign = true)
	private Member student;
	@DatabaseField(columnName = "formation_id", foreign = true)
	private Formation formation;
	@DatabaseField(columnName = "year")
	private String year;
	
	public Register() {}

	public Member getStudent() {
		return student;
	}

	public void setStudent(Member student) {
		this.student = student;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Register [student=" + student + ", formation=" + formation + ", year=" + year + "]";
	}
	
}
