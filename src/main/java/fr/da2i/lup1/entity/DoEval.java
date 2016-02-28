package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DoEval {

	private double mark;
	private int studentId;
	private int evaluationId;
	
	public DoEval(double m, int s, int e) {
		this.mark = m;
		this.studentId = s;
		this.evaluationId = e;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(int evaluationId) {
		this.evaluationId = evaluationId;
	}
}
