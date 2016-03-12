package fr.da2i.lup1.entity.note;

import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bulletin extends Assessable<Ue, String> {
	
	private String login;
	
	public Bulletin() {}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	@JsonProperty(value = "ues")
	public Set<Ue> getAssessables() {
		return super.getAssessables();
	}
	
	public int indexOf(Ue ue) {
		int i = 0;
		Iterator<Ue> it = getAssessables().iterator();
		while (it.hasNext()) {
			if (ue.equals(it.next())) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public Ue get(int index) {
		int i = 0;
		Iterator<Ue> it = getAssessables().iterator();
		while (it.hasNext()) {
			if (i == index) {
				return it.next();
			}
			i++;
		}
		return null;
	}


	@Override
	public String toString() {
		return "Bulletin [login=" + login + "]";
	}

	@Override
	public String getId() {
		return login;
	}

	@Override
	public void setId(String id) {
		this.login = id;
	}
	

}
