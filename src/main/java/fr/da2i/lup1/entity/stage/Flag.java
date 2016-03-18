package fr.da2i.lup1.entity.stage;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "flag")
public class Flag extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "flag_id", id = true)
	private int flagId;
	@DatabaseField(columnName = "label")
	private String label;
	
	public Flag(int f, String l) {
		this.flagId = f;
		this.label = l;
	}
	
	public Flag() {}
	
	@Override
	public Integer getId() {
		return this.flagId;
	}
	@Override
	public void setId(Integer id) {
		this.flagId = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}	
}
