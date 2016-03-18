package fr.da2i.lup1.entity.stage;

import java.sql.Date;

import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "internship")
public class Internship extends Identifiable<Integer> {

	@DatabaseField(columnName = "internship_id", generatedIdSequence = "internship_internship_id_seq")
	protected int internshipId;
	@DatabaseField(columnName = "formation_id")
	private int formationId;
	@DatabaseField(columnName = "year")
	private String year;
	@DatabaseField(columnName = "title")
	protected String title;
	@DatabaseField(columnName = "missions")
	protected String missions;
	@DatabaseField(columnName = "description")
	protected String description;
	@DatabaseField(columnName = "duration")
	protected double duration;
	@DatabaseField(columnName = "begin_date")
	protected Date beginDate;
	@DatabaseField(columnName = "siret")
	protected String siret;
	@DatabaseField(columnName = "technology")
	protected String technology;
	
	public Internship(int id, int f, String y, String title, String m, String desc, double dur, Date b, String s, String tec) {
		this.internshipId = id;
		this.formationId = f;
		this.year = y;
		this.title = title;
		this.missions = m;
		this.description = desc;
		this.duration = dur;
		this.beginDate = b;
		this.siret = s;
		this.technology = tec;
	}
	
	public Internship() {}
	
	@Override
	public Integer getId() {
		return this.internshipId;
	}

	@Override
	public void setId(Integer id) {
		this.internshipId = id;
	}
	
	public int getFormationId() {
		return formationId;
	}

	public void setFormationId(int formationId) {
		this.formationId = formationId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getInternshipId() {
		return this.internshipId;
	}

	public void setInternshipId(int id) {
		this.internshipId = id;
	}

	public String getTitle() {
		return Strings.nullToEmpty(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMissions() {
		return Strings.nullToEmpty(missions);
	}

	public void setMissions(String missions) {
		this.missions = missions;
	}

	public String getDescription() {
		return Strings.nullToEmpty(description);
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
		return Strings.nullToEmpty(siret);
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
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
