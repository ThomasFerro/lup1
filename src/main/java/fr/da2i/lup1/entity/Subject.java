package fr.da2i.lup1.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "subject")
public class Subject implements Identifiable<Integer> {
	
	@DatabaseField(columnName = "subject_id", id = true)
	private int subjectId;
	@DatabaseField(columnName = "name")
	private String name;
	@DatabaseField(columnName = "coeff")
	private double coeff;
	@DatabaseField(columnName = "ue_id", foreign = true)
	private int ueId;
	
	public Subject(int subjectId, String name, double coeff, int user) {
		this.subjectId = subjectId;
		this.name = name;
		this.coeff = coeff;
		this.ueId = user;
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

	public int getUeId() {
		return ueId;
	}

	public void setUeId(int ueId) {
		this.ueId = ueId;
	}
}
