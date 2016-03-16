package fr.da2i.lup1.entity.note;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.da2i.lup1.entity.formation.Member;

@XmlRootElement
public class Bulletin {
	
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
