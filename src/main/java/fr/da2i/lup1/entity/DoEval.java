package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DoEval {

	private double mark;
	private int studentId;
	private int evaluationId;
	
	public DoEval(double mark, int studentId, int evaluationId) {
		this.mark = mark;
		this.studentId = studentId;
		this.evaluationId = evaluationId;
	}
	
	public DoEval() {}

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
