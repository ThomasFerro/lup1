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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mark_by_student")
public class Mark {
	
	@DatabaseField(columnName = "ue_id")
	private Integer ueId;
	@DatabaseField(columnName = "subject_id")
	private Integer subjectId;
	@DatabaseField(columnName = "student_id")
	private Integer studentId;
	@DatabaseField(columnName = "mark")
	private double mark;
	@DatabaseField(columnName = "coeff_eval")
	private double coeffEval;
	@DatabaseField(columnName = "coeff_subject")
	private double coeffSubject;
	@DatabaseField(columnName = "coeff_ue")
	private double coeffUe;
	@DatabaseField(columnName = "semester")
	private String semester;
	
	public Mark() {}

	/**
	 * @return the ue
	 */
	public Integer getUeId() {
		return ueId;
	}

	/**
	 * @param ue the ue to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	/**
	 * @return the subject
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the student
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the mark
	 */
	public double getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(double mark) {
		this.mark = mark;
	}

	/**
	 * @return the coeffEval
	 */
	public double getCoeffEval() {
		return coeffEval;
	}

	/**
	 * @param coeffEval the coeffEval to set
	 */
	public void setCoeffEval(double coeffEval) {
		this.coeffEval = coeffEval;
	}

	/**
	 * @return the coeffSubject
	 */
	public double getCoeffSubject() {
		return coeffSubject;
	}

	/**
	 * @param coeffSubject the coeffSubject to set
	 */
	public void setCoeffSubject(double coeffSubject) {
		this.coeffSubject = coeffSubject;
	}

	/**
	 * @return the coeffUe
	 */
	public double getCoeffUe() {
		return coeffUe;
	}

	/**
	 * @param coeffUe the coeffUe to set
	 */
	public void setCoeffUe(double coeffUe) {
		this.coeffUe = coeffUe;
	}

	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	@Override
	public String toString() {
		return "Mark [ue=" + ueId + ", subject=" + subjectId + ", student="
				+ studentId + ", mark=" + mark + ", coeffEval=" + coeffEval
				+ ", coeffSubject=" + coeffSubject + ", coeffUe=" + coeffUe
				+ ", semester=" + semester + "]";
	}

}
