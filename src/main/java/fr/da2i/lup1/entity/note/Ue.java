package fr.da2i.lup1.entity.note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "ue")
public class Ue extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "ue_id", id = true)
	private Integer ueId;
	@DatabaseField(columnName = "name")
	private String name;
	
	private double coeff;
	private List<Subject> subjects = new ArrayList<>();
	
	public Ue(int ueId, String nameId) {
		this.ueId = ueId;
		this.name = nameId;
	}
	
	public Ue() {}

	public Integer getId() {
		return ueId;
	}

	public void setId(Integer ueId) {
		this.ueId = ueId;
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
	
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
	public boolean add(Subject subject) {
		return getSubjects().add(subject);
	}
	
	public boolean remove(Subject subject) {
		return getSubjects().remove(subject);
	}
	
	public Subject get(int idx) {
		return getSubjects().get(idx);
	}
	
	public int indexOf(Subject sub) {
		return getSubjects().indexOf(sub);
	}
	
	public boolean addAll(Collection<Subject> subjects) {
		return subjects.addAll(subjects);
	}

	@Override
	public String toString() {
		return "Ue [ueId=" + ueId + ", name=" + name + "]";
	}
	
}
