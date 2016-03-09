package fr.da2i.lup1.entity.stage;

import java.sql.Timestamp;

import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "internship_log")
public class InternshipLog extends Identifiable<Integer> {

	@DatabaseField(columnName = "internship_log_id", id = true)
	private int internshipLogId;
	@DatabaseField(columnName = "date_log")
	private Timestamp dateLog;
	@DatabaseField(columnName = "quote")
	private String quote;
	@DatabaseField(columnName = "flag_id")
	private int flagId;
	@DatabaseField(columnName = "member_id")
	private int memberId;
	@DatabaseField(columnName = "internship_id", foreign = true, foreignAutoRefresh = true)
	private Internship internship;
	
	public InternshipLog(int id, Timestamp d, String q, int f, int m, Internship i) {
		this.internshipLogId = id;
		this.dateLog = d;
		this.quote = q;
		this.flagId = f;
		this.memberId = m;
		this.internship = i;
	}
	
	public InternshipLog() {}

	@Override
	public Integer getId() {
		return this.internshipLogId;
	}

	@Override
	public void setId(Integer id) {
		this.internshipLogId = id;
	}

	public Timestamp getDateLog() {
		return dateLog;
	}

	public void setDateLog(Timestamp dateLog) {
		this.dateLog = dateLog;
	}

	public String getQuote() {
		return Strings.nullToEmpty(quote);
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public int getFlagId() {
		return flagId;
	}

	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Internship getInternshipId() {
		return internship;
	}

	public void setInternshipId(Internship internshipId) {
		this.internship = internshipId;
	}
	
	public String toString() {
		return "InternshipLog ["
				+ "internship_log_id=" + getId() + ", "
				+ "date_log=" + getDateLog() + ", "
				+ "quote=" + getQuote() + ", "
				+ "flag_id=" + getFlagId() + ", "
				+ "member_id=" + getMemberId() + ", "
				+ "internship_id=" + getInternshipId()
				+ "]";
	}
}
