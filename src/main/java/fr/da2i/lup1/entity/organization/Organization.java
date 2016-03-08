package fr.da2i.lup1.entity.organization;

import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "organization")
public class Organization extends Identifiable<String>{

	@DatabaseField(columnName = "siret", id = true)
	private String siret;
	@DatabaseField(columnName = "name")
	private String name;
	@DatabaseField(columnName = "address")
	private String address;
	@DatabaseField(columnName = "phone")
	private String phone;
	@DatabaseField(columnName = "fax")
	private String fax;
	
	public Organization(String siret, String name, String address, String phone, String fax) {
		this.siret = siret;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}
	
	public Organization() {}
	
	@Override
	public String getId() {
		return Strings.nullToEmpty(siret);
	}
	@Override
	public void setId(String id) {
		this.siret = id;
	}

	public String getSiret() {
		return Strings.nullToEmpty(siret);
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getName() {
		return Strings.nullToEmpty(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Strings.nullToEmpty(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return Strings.nullToEmpty(phone);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return Strings.nullToEmpty(fax);
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String toString() {
		return "{"
				+ "\"siret\":\"" + getId() + "\", "
				+ "\"name\":\"" + getName() + "\", "
				+ "\"address\":\"" + getAddress() + "\", "
				+ "\"phone\":\"" + getPhone() + "\", "
				+ "\"fax\":\"" + getFax() + "\""
				+ "}";
	}
}
