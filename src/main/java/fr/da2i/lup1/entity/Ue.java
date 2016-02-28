package fr.da2i.lup1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ue {
	
	private int ueId;
	private String name;
	
	public Ue(int u, String n) {
		this.ueId = u;
		this.name = n;
	}

	public int getUeId() {
		return ueId;
	}

	public void setUeId(int ueId) {
		this.ueId = ueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
