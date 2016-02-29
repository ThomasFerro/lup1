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
package fr.da2i.lup1.util;

import java.sql.Timestamp;
import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "securid")
public class SecurId implements Identifiable<String> {
	
	public static final String CLIENT_TOKEN_KEY = "Authentication";
	public static final long NEXT_EXPIRATION_MILLIS = 600000L;
	
	@DatabaseField(columnName = "login", id = true, foreign = true)
	private String login;
	@DatabaseField(columnName = "token")
	private String token;
	@DatabaseField(columnName = "expiration_date")
	private Timestamp expirationDate;
	
	public SecurId(String login, UUID token, Timestamp expirationDate) {
		this.login = login;
		this.token = token.toString();
		this.expirationDate = expirationDate;
	}
	
	public SecurId(String login) {
		this.login = login;
		this.regenerate();
	}
	
	public SecurId() {}
	
	@Override
	public String getId() {
		return login;
	}

	@Override
	public void setId(String id) {
		this.login = id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(UUID token) {
		this.token = token.toString();
	}
	
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public boolean hasExpire() {
		return expirationDate.before(new Timestamp(System.currentTimeMillis()));
	}
	
	public void regenerate() {
		token = UUID.randomUUID().toString();
		expirationDate = new Timestamp(System.currentTimeMillis() + NEXT_EXPIRATION_MILLIS);
	}
	
	@Override
	public String toString() {
		return "SecurId[login=" + login + ", token=" + token + ", expirationDate=" + expirationDate + "]";
	}

}
