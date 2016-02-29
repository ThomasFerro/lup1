package fr.da2i.lup1.entity;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "evaluation")
public class Evaluation implements Identifiable<Integer> {
	
	@DatabaseField(columnName = "evaluation_id", id = true)
	private int evaluationId;
	@DatabaseField(columnName = "name")
	private String name;
	@DatabaseField(columnName = "name")
	private double coeff;
	@DatabaseField(columnName = "teacher_id", foreign = true)
	private int teacherId;
	@DatabaseField(columnName = "subject_id", foreign = true)
	private int subjectId;
	@DatabaseField(columnName = "date_eval")
	private Date dateEval;
	
	public Evaluation(int evaluationId, String name, double coeff, int teacherId, int subjectId, Date dateEval) {
		this.evaluationId = evaluationId;
		this.name = name;
		this.coeff = coeff;
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.dateEval = dateEval;
	}
	
	public Evaluation() {}

	public Integer getId() {
		return evaluationId;
	}

	public void setId(Integer evaluationId) {
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
