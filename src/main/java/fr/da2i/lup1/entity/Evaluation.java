package fr.da2i.lup1.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Evaluation {
	
	private int evaluationId;
	private String name;
	private double coeff;
	private int teacherId;
	private int subjectId;
	private Date dateEval;
	
	public Evaluation(int e, String n, double c, int t, int s, Date d) {
		this.evaluationId = e;
		this.name = n;
		this.coeff = c;
		this.teacherId = t;
		this.subjectId = s;
		this.dateEval = d;
	}

	public int getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(int evaluationId) {
		this.evaluationId = evaluationId;
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

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Date getDateEval() {
		return dateEval;
	}

	public void setDateEval(Date dateEval) {
		this.dateEval = dateEval;
	}
}
