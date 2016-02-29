package fr.da2i.lup1.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "credential")
public class Credential implements Identifiable<String> {

	@DatabaseField(columnName = "login", id = true)
	private String login;
	@DatabaseField(columnName = "password")
	private String password;
	
	public Credential(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Credential() {}
	
	@Override
	public String getId() {
		if (login == null) {
			login = "";
		}
		return login;
	}

	@Override
	public void setId(String id) {
		this.login = id;
	}

	public String getLogin() {
		return getId();
	}

	public void setLogin(String login) {
		setId(login);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
