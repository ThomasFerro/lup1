package fr.da2i.lup1.entity.note;

import javax.xml.bind.annotation.XmlRootElement;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@XmlRootElement
@DatabaseTable(tableName = "ue_promotion")
public class UePromotion {
	
	@DatabaseField(columnName = "promotion_id", foreign = true)
	private int promotionId;
	@DatabaseField(columnName = "semester")
	private int semester;
	@DatabaseField(columnName = "coeff")
	private double coeff;
	@DatabaseField(columnName = "ueId", foreign = true)
	private int ueId;
	
	public UePromotion(int promotionId, int semester, double coeff, int ueId) {
		this.promotionId = promotionId;
		this.semester = semester;
		this.coeff = coeff;
		this.ueId = ueId;
	}
	
	public UePromotion() {}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
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
