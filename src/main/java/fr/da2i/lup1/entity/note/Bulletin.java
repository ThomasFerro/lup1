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

import java.util.List;

import fr.da2i.lup1.entity.formation.Member;
import fr.da2i.lup1.util.Identifiable;

public class Bulletin extends Identifiable<String> {
	
	private Member student;
	private List<Mark> marks;
	
	public Bulletin() {}

	public Member getStudent() {
		return student;
	}

	public void setStudent(Member student) {
		this.student = student;
	}
	
	public List<Mark> getMarks() {
		return marks;
	}
	
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	@Override
	public String getId() {
		return student.getLogin();
	}

	@Override
	public void setId(String id) {
		// Nothing to do
	}

}
