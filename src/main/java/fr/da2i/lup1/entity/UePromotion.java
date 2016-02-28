package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UePromotion {
	
	private int promotionId;
	private int semester;
	private double coeff;
	private int ueId;
	
	public UePromotion(int p, int s, double c, int u) {
		this.promotionId = p;
		this.semester = s;
		this.coeff = c;
		this.ueId = u;
	}

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
