package fr.da2i.lup1.entity.note;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "ue")
public class Ue extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "ue_id", id = true)
	private Integer ueId;
	@DatabaseField(columnName = "name")
	private String name;
	private Set<Subject> subjects = new HashSet<>();
	
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
	
	/**
	 * @return the subjects
	 */
	public Set<Subject> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public boolean contains(Subject subject) {
		return subjects.contains(subject);
	}
	
	public boolean add(Subject subject) {
		return subjects.add(subject);
	}
	
	public boolean remove(Subject subject) {
		return subjects.remove(subject);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		return ueId.equals(((Ue) o).ueId);
	}

	@Override
	public String toString() {
		return "Ue [ueId=" + ueId + ", name=" + name + "]";
	}
	
}
