package fr.da2i.lup1.entity.note;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "subject")
public class Subject extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "subject_id", id = true)
	private Integer subjectId;
	@DatabaseField(columnName = "name")
	private String name;
	@DatabaseField(columnName = "coeff")
	private double coeff;
	private Set<Mark> marks = new HashSet<Mark>();
	
	public Subject(int subjectId, String name, double coeff, int user) {
		this.subjectId = subjectId;
		this.name = name;
		this.coeff = coeff;
	}
	
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
	
	/**
	 * @return the marks
	 */
	public Set<Mark> getMarks() {
		return marks;
	}

	/**
	 * @param marks the marks to set
	 */
	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public boolean contains(Mark mark) {
		return marks.contains(mark);
	}
	
	public boolean add(Mark mark) {
		return marks.add(mark);
	}
	
	public boolean remove(Mark mark) {
		return marks.remove(mark);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		return subjectId.equals(((Subject) o).subjectId);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + ", coeff=" + coeff + "]";
	}
	
}
