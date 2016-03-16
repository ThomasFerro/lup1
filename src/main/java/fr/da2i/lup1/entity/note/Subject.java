package fr.da2i.lup1.entity.note;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "subject")
public class Subject extends Identifiable<Integer> {
	
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
	
}
