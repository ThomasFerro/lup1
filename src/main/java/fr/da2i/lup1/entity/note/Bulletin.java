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

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.da2i.lup1.entity.formation.Member;

@XmlRootElement
public class Bulletin implements Average {
	
	private Member student;
	private List<Ue> studentUes;
	
	public Bulletin() {
		this.studentUes = new ArrayList<>();
	}
	
	/**
	 * @return the student
	 */
	public Member getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Member student) {
		this.student = student;
	}

	/**
	 * @return the studentUes
	 */
	@JsonProperty(value = "ues")
	public List<Ue> getStudentUes() {
		return studentUes;
	}

	/**
	 * @param studentUes the studentUes to set
	 */
	public void setStudentUes(List<Ue> studentUes) {
		this.studentUes = studentUes;
	}
	
	public int indexOf(Ue ue) {
		return studentUes.indexOf(ue);
	}
	
	public Ue get(int idx) {
		return studentUes.get(idx);
	}
	
	public void add(Mark mark) {
		Ue ue = mark.getUe();
		Subject sub = mark.getSubject();
		
		int ueIdx = studentUes.indexOf(ue);
		int subIdx;
		if (ueIdx >= 0) {
			subIdx = studentUes.get(ueIdx).indexOf(sub);
			if (subIdx >= 0) {
				studentUes.get(ueIdx).get(subIdx).add(mark);
			}
			else {
				studentUes.get(ueIdx).add(sub);
				sub.add(mark);
				sub.setCoeff(mark.getCoeffSubject());
			}
		}
		else {
			studentUes.add(ue);
			ue.add(sub);
			ue.setCoeff(mark.getCoeffUe());
			sub.add(mark);
			sub.setCoeff(mark.getCoeffSubject());
		}
	}
	
	@Override
	@JsonProperty("avg")
	public double avg() {
		double avg = 0;
		double sum = 0;
		for (Ue ue : studentUes) {
			sum += ue.getCoeff();
			avg += ue.avg() * ue.getCoeff();
		}
		return avg / sum;
	}
	
//	public boolean add(Ue ue) {
//		int idx = indexOf(ue);
//		if (idx == -1) {
//			return studentUes.add(ue);
//		}
//		Ue existingUe = studentUes.get(idx);
//		for (Subject s : ue.getAssessables()) {
//			if (existingUe.contains(s)) {
//				existingUe.
//			}
//		}
//		return studentUes.get(idx).addAll(ue.getAssessables());
//	}

}
