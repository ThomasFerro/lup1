package fr.da2i.lup1.entity.formation;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "formation")
public class Formation extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "formation_id", id = true)
	private int formationId;
	@DatabaseField(columnName = "name")
	private String name;
	
	public Formation(int formationId, String name) {
		this.formationId = formationId;
		this.name = name;
	}
	
	public Formation() {}

	public Integer getId() {
		return formationId;
	}

	public void setId(Integer formationId) {
		this.formationId = formationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
