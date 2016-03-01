package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Promotion {
	
	private int promotionId;
	private int year;
	private int formationId;
	private int responsableId;
	
	public Promotion(int promotionId, int year, int formationId, int responsableId) {
		this.promotionId = promotionId;
		this.year = year;
		this.formationId = formationId;
		this.responsableId = responsableId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
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
