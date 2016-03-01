package fr.da2i.lup1.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "ue")
public class Ue extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "ue", id = true)
	private int ueId;
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
	
}
