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
package fr.da2i.lup1.entity.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import fr.da2i.lup1.entity.formation.Member;
import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "credential")
public class Credential extends Identifiable<String> implements Principal {

	@DatabaseField(columnName = "login", id = true)
	private String login;
	@DatabaseField(columnName = "password")
	private String password;
	@DatabaseField(columnName = "member_id", foreign = true, foreignAutoRefresh = true)
	private Member member;
	@ForeignCollectionField @JsonIgnore
	private ForeignCollection<Role> roles;

	public Credential(String login, String password, Member member) {
		this.login = login;
		this.password = password;
		this.member = member;
	}

	public Credential() {}

	@Override
	public String getName() {
		return getId();
	}

	@Override
	public String getId() {
		return Strings.nullToEmpty(login);
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
		return Strings.nullToEmpty(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<String> getRoles() {
		List<String> roleNames = new ArrayList<>();
		if (roles != null) {
			Iterator<Role> it = roles.iterator();
			while (it.hasNext()) {
				roleNames.add(it.next().getRoleName());
			}
		}
		return roleNames;
	}

	@Override
	public String toString() {
		return "Credential [login=" + getLogin() + ", member=" + getMember() + ", roles=" + getRoles() + "]";
	}

}
