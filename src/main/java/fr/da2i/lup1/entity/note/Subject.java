package fr.da2i.lup1.entity.note;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "subject")
public class Subject extends Assessable<Mark> {
	
	@DatabaseField(columnName = "subject_id", id = true)
	private Integer subjectId;
	@DatabaseField(columnName = "name")
	private String name;
	
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
	
	@JsonProperty(value = "marks")
	public Set<Mark> getAssessables() {
		return super.getAssessables();
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + ", coeff=" + getCoeff() + "]";
	}
	
}
