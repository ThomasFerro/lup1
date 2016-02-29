package fr.da2i.lup1.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "member")
public class Member implements Identifiable<Integer> {
	
	@DatabaseField(columnName = "id", id = true)
	private int id;
	@DatabaseField(columnName = "first_name")
	private String firstName;
	@DatabaseField(columnName = "last_name")
	private String lastName;
	@DatabaseField(columnName = "role", foreign = true)
	private String role;
	@DatabaseField(columnName = "email")
	private String email;
	@DatabaseField(columnName = "birthday")
	private String birthday;
	@DatabaseField(columnName = "login", foreign = true)
	private String login;
	@DatabaseField(columnName = "phone")
	private String phone;
	@DatabaseField(columnName = "siret", foreign = true)
	private String siret;
	@DatabaseField(columnName = "picture")
	private String picture;
	@DatabaseField(columnName = "address")
	private String address;
	
	public Member(int id, String firstName, String lastName, String role, String email, String birthday, String login, String phone, String siret, String picture, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.phone = phone;
		this.siret = siret;
		this.picture = picture;
		this.address = address;
	}
		
	public Member() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
