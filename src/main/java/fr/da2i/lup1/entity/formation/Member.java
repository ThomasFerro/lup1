/**
 * This file is part of lup1.
 *
 * lup1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * lup1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with lup1.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.da2i.lup1.entity.formation;

import java.sql.Date;

import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "member")
public class Member extends Identifiable<Integer> {
	
	@DatabaseField(columnName = "member_id", generatedIdSequence = "member_member_id_seq")
	private int id;
	@DatabaseField(columnName = "first_name")
	private String firstName;
	@DatabaseField(columnName = "last_name")
	private String lastName;
	@DatabaseField(columnName = "email")
	private String email;
	@DatabaseField(columnName = "birthday")
	private Date birthday;
	@DatabaseField(columnName = "phone")
	private String phone;
	@DatabaseField(columnName = "siret")
	private String siret;
	@DatabaseField(columnName = "picture")
	private String picture;
	@DatabaseField(columnName = "address")
	private String address;
		
	public Member() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return Strings.nullToEmpty(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return Strings.nullToEmpty(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return Strings.nullToEmpty(email);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSiret() {
		return Strings.nullToEmpty(siret);
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getPhone() {
		return Strings.nullToEmpty(phone);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return Strings.nullToEmpty(picture);
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return Strings.nullToEmpty(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [id=" + getId() + ", firstName=" + getFirstName() + ", lastName="
				+ getLastName() + ", email=" + getEmail()
				+ ", birthday=" + getBirthday() + ", phone="
				+ getPhone() + ", siret=" + getSiret() + ", picture=" + getPicture()
				+ ", address=" + getAddress() + "]";
	}
	
	
	
}
