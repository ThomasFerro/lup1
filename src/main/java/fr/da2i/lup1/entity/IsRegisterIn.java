package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IsRegisterIn {

	private int studentId;
	private int promotionId;
	
	public IsRegisterIn(int studentId, int promotionId) {
		this.studentId = studentId;
		this.promotionId = promotionId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
}
