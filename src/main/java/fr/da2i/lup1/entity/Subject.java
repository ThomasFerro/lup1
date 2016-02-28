package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subject {
	
	private int subjectId;
	private String name;
	private double coeff;
	private int ueId;
	
	public Subject(int s, String n, double c, int u) {
		this.subjectId = s;
		this.name = n;
		this.coeff = c;
		this.ueId = u;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
