package fr.da2i.lup1.entity.formation;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "promotion")
public class Promotion extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "promotion_id", id = true)
	private int promotionId;
	@DatabaseField(columnName = "year")
	private int year;
	@DatabaseField(columnName = "formation_id")
	private int formationId;
	@DatabaseField(columnName = "responsable_id")
	private int responsableId;
	
	public Promotion(int promotionId, int year, int formationId, int responsableId) {
		this.promotionId = promotionId;
		this.year = year;
		this.formationId = formationId;
		this.responsableId = responsableId;
	}

	public Integer getId() {
		return promotionId;
	}

	public void setId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFormationId() {
		return formationId;
	}

	public void setFormationId(int formationId) {
		this.formationId = formationId;
	}

	public int getResponsableId() {
		return responsableId;
	}

	public void setResponsableId(int responsableId) {
		this.responsableId = responsableId;
	}
}
