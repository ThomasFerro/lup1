package fr.da2i.lup1.entity.stage;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "internship_by_formation")
public class InternshipByFormation {
	
	@DatabaseField(columnName = "formation_id", foreign = true)
	private int formationId;
	@DatabaseField(columnName = "internship_id")
	private int internshipId;
	
	public InternshipByFormation(int f, int i) {
		this.formationId = f;
		this.internshipId = i;
	}
	
	public InternshipByFormation() {}

	public int getFormationId() {
		return formationId;
	}

	public void setFormationId(int formationId) {
		this.formationId = formationId;
	}

	public int getInternshipId() {
		return internshipId;
	}

	public void setInternshipId(int internshipId) {
		this.internshipId = internshipId;
	}
}
