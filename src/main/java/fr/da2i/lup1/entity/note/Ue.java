package fr.da2i.lup1.entity.note;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ue")
public class Ue extends Assessable<Subject> {
	
	@DatabaseField(columnName = "ue_id", id = true)
	private Integer ueId;
	@DatabaseField(columnName = "name")
	private String name;
	
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
	
	@JsonProperty(value = "subjects")
	public Set<Subject> getAssessables() {
		return super.getAssessables();
	}

	@Override
	public String toString() {
		return "Ue [ueId=" + ueId + ", name=" + name + "]";
	}
	
}
