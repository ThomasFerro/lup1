package fr.da2i.lup1.entity.stage;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "internship")
public class Internship extends Identifiable<Integer> {

	@DatabaseField(columnName = "internship_id", id = true)
	private int internship_id;
	@DatabaseField(columnName = "title")
	private String title;
	@DatabaseField(columnName = "missions")
	private String missions;
	@DatabaseField(columnName = "description")
	private String description;
	@DatabaseField(columnName = "duration")
	private double duration;
	@DatabaseField(columnName = "begin_date")
	private Date beginDate;
	@DatabaseField(columnName = "siret")
	private String siret;
	
	public Internship(int id, String t, String m, String desc, double dur, Date b, String s) {
		this.internship_id = id;
		this.title = t;
		this.missions = m;
		this.description = desc;
		this.duration = dur;
		this.beginDate = b;
		this.siret = s;
	}
	
	public Internship() {}
	
	@Override
	public Integer getId() {
		return this.internship_id;
	}

	@Override
	public void setId(Integer id) {
		this.internship_id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMissions() {
		return missions;
	}

	public void setMissions(String missions) {
		this.missions = missions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}
	
	@Override
	public String toString() {
		return "Internship ["
				+ "internship_id=" + getId() + ", "
				+ "title=" + getTitle() + ", "
				+ "missions=" + getMissions() + ", "
				+ "description=" + getDescription() + ", "
				+ "duration=" + getDuration() + ", "
				+ "begin_date=" + getBeginDate() + ", "
				+ "siret=" + getSiret()
				+ "]";
	}
}
