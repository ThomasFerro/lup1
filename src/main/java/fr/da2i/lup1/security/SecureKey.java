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

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import fr.da2i.lup1.util.Identifiable;

@DatabaseTable(tableName = "secure_key")
public class SecureKey extends Identifiable<String> {
	
	public final static int SHARED_KEY_SIZE = 32;
	public final static long TTL = 600000L;
	
	@DatabaseField(columnName = "login", id = true)
	private String login;
	@DatabaseField(columnName = "shared_key", dataType = DataType.BYTE_ARRAY)
	private byte[] sharedKey;
	
	private String[] roles;
	
	public SecureKey(String login) {
		this.login = login;
	}
	
	public SecureKey() {}
	
	public String getId() {
		return login;
	}
	
	public void setId(String login) {
		this.login = login;
	}
	
	public byte[] getSharedKey() {
		return sharedKey;
	}
	
	public String[] getRoles() {
		return roles;
	}
	
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public void setSharedKey(byte[] sharedKey) {
		this.sharedKey = sharedKey;
	}
	
	public boolean verify(SignedJWT signedJWT) {
		try {
			JWSVerifier verifier = new MACVerifier(sharedKey);
			return signedJWT.verify(verifier);
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean hasExpire(SignedJWT signedJWT) {
		try {
			Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
			return exp.before(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public SignedJWT regenerate() {
		try {
			SecureRandom random = new SecureRandom();
			sharedKey = new byte[SHARED_KEY_SIZE];
			random.nextBytes(sharedKey);
			// Date d'expiration du token
			Timestamp exp = new Timestamp(System.currentTimeMillis() + TTL);
			// Création de la signature avec l'algorithme HMAC
			JWSSigner signer = new MACSigner(sharedKey);
			// Préparation du payload
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.issuer(login)
				.claim("roles", roles)
				.expirationTime(exp)
				.build();
			// Préparation du token
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			// Signature du token
			signedJWT.sign(signer);
			return signedJWT;
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SignedJWT parse(String signedJWT) {
		try {
			return SignedJWT.parse(signedJWT);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
