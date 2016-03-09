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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mark_by_student")
public class Mark {
	
	@DatabaseField(columnName = "ue_id", foreign = true, foreignAutoRefresh = true)
	private Ue ue;
	@DatabaseField(columnName = "subject_id", foreign = true, foreignAutoRefresh = true)
	private Subject subject;
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
	private Integer semester;
	@DatabaseField(columnName = "formation_id")
	private Integer formationId;
	@DatabaseField(columnName = "year")
	private String year;
	
	public Mark() {}

	/**
	 * @return the ue
	 */
	@JsonIgnore
	public Ue getUe() {
		return ue;
	}

	/**
	 * @param ue the ue to set
	 */
	public void setUe(Ue ue) {
		this.ue = ue;
	}

	/**
	 * @return the subject
	 */
	@JsonIgnore
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the student
	 */
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
	public Integer getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(Integer semester) {
		this.semester = semester;
	}
	
	/**
	 * @return the formationId
	 */
	@JsonIgnore
	public Integer getFormationId() {
		return formationId;
	}

	/**
	 * @param formationId the formationId to set
	 */
	public void setFormationId(Integer formationId) {
		this.formationId = formationId;
	}

	/**
	 * @return the year
	 */
	@JsonIgnore
	public String getYear() {
		return Strings.nullToEmpty(year);
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		Mark m = (Mark) o;
		return ue.equals(m.ue) && subject.equals(m.subject) && studentId.equals(m.studentId)
				&& formationId.equals(m.formationId) && year.equals(m.year)
				&& semester.equals(m.semester);
	}

	@Override
	public String toString() {
		return "Mark [ue=" + ue + ", subject=" + subject
				+ ", studentId=" + studentId + ", mark=" + mark
				+ ", coeffEval=" + coeffEval + ", coeffSubject=" + coeffSubject
				+ ", coeffUe=" + coeffUe + ", semester=" + semester
				+ ", formationId=" + formationId + ", year=" + year + "]";
	}

}
