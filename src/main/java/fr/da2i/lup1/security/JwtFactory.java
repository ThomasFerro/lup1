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
package fr.da2i.lup1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.sql.Timestamp;
import java.util.Map;

import javax.inject.Singleton;

/**
 * @author Edouard
 *
 */
@Singleton
public final class JwtFactory {
	
	public static final long TTL = 600000L;
	
	private Key key;
	
	public JwtFactory() {
		this.key = MacProvider.generateKey();
	}
	
	public String build(String iss, Map<String, Object> map) {
		Claims claims = new DefaultClaims();
		claims.putAll(map);
		claims.setSubject("lup1");
		claims.setIssuer(iss);
		claims.setExpiration(new Timestamp(System.currentTimeMillis() + TTL));
		return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).signWith(SignatureAlgorithm.HS512, key).compact();
	}
	
	public Claims parse(String jwtString) {
		return Jwts.parser().setSigningKey(key).parseClaimsJwt(jwtString).getBody();
	}
	
	public boolean check(String jwtString) {
		try {
			Jwts.parser().setSigningKey(key).parse(jwtString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
