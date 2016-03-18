package fr.da2i.lup1.entity.note;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.entity.formation.Formation;

@DatabaseTable(tableName = "ue_promotion")
public class UePromotion {
	
	@DatabaseField(columnName = "formation_id", foreign = true)
	private Formation formation;
	@DatabaseField(columnName = "year")
	private String year;
	@DatabaseField(columnName = "semester")
	private int semester;
	@DatabaseField(columnName = "ue_id")
	private Integer ueId;
	
	public UePromotion(Formation f, String y, int s, int u) {
		this.formation = f;
		this.year = y;
		this.semester = s;
		this.ueId = u;
	}
	
	public UePromotion() {}

	/**
	 * @return the formation
	 */
	public Formation getFormation() {
		return formation;
	}

	/**
	 * @param formation the formation to set
	 */
	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Integer getUeId() {
		return ueId;
	}

	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}
	
}
