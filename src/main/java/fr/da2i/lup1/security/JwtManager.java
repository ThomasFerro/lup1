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

import fr.da2i.lup1.entity.security.Credential;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.inject.Singleton;

/**
 * @author Edouard
 *
 */
@Singleton
public final class JwtManager {
	
	public static final long TTL = 600000L;
	
	private Key key;
	
	public JwtManager() {
		this.key = MacProvider.generateKey();
	}
	
	public boolean hasExpire(Claims claims) {
		return claims.getExpiration().before(new Date(System.currentTimeMillis()));
	}
	
	public String compact(Claims claims) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).signWith(SignatureAlgorithm.HS512, key).compact();
	}
	
	public String compact(Credential credential) {
		return compact(buildFrom(credential));
	}
	
	public Claims build(String sub) {
		Claims claims = new DefaultClaims();
		claims.setSubject(sub);
		claims.setIssuer("lup1");
		claims.setExpiration(new Date(System.currentTimeMillis() + TTL));
		return claims;
	}
	
	public Claims buildFrom(Credential credential) {
		Claims claims = build(credential.getLogin());
		claims.put("roles", credential.getRoles());
		return claims;
	}
	
	public String regenerate(Claims from) {
		Claims claims = new DefaultClaims();
		for (Map.Entry<String, Object> entry : from.entrySet()) {
			claims.put(entry.getKey(), entry.getValue());
		}
		claims.setSubject(from.getSubject());
		claims.setIssuer(from.getIssuer());
		claims.setExpiration(new Date(System.currentTimeMillis() + TTL));
		return compact(claims);
	}
	
	public Claims parse(String jwtString) {
		try {
			return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtString).getBody();
		} catch (ClaimJwtException e) {
			return e.getClaims();
		} catch (Exception e) {
			return null;
		}
	}

}
