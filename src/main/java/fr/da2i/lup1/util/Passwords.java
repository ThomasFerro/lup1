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

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Offre un système de cryptage des mots de passe.
 * 
 * This class is based on :
 * 
 * Password Hashing With PBKDF2 (http://crackstation.net/hashing-security.htm).
 * Copyright (c) 2013, Taylor Hornby
 * 
 * PBKDF2 (Password-Based Key Derivation Function 2) is a key derivation function
 * that is part of RSA Laboratories' Public-Key Cryptography Standards (PKCS) series, specifically PKCS #5 v2.0,
 * also published as Internet Engineering Task Force's RFC 2898.
 * It replaces an earlier standard, PBKDF1, which could only produce derived keys up to 160 bits long.
 */
public final class Passwords {
	
	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	
	public static final int HEXA_SIZE = 16;
	public static final int SALT_BYTE_SIZE = 24;
	public static final int HASH_BYTE_SIZE = 24;
	public static final int PBKDF2_ITERATIONS = 1000;

	public static final String SEPARATOR = ":";
	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private Passwords() {}
	
	/**
	 * "Sale" un mot de passe
	 * 
	 * @param	password
	 * 			le mot de passe à saler
	 * 
	 * @return	un tableau d'octets représentant un mot de passe salé
	 */
	private static byte[] salt(char[] password) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return salt;
	}
	
	/**
	 * Hash un mot de passe
	 * 
	 * @param	password
	 * 			le mot de passe à hasher
	 * 
	 * @return	le mot de passe hashé sous la forme d'une chaîne de caractères (iterations:salt:hash)
	 * 
	 * @throws	NoSuchAlgorithmException
	 * @throws	InvalidKeySpecException
	 */
	public static String hash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = salt(password);
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		return PBKDF2_ITERATIONS + SEPARATOR + toHex(salt) + SEPARATOR +  toHex(hash);
	}
	
	/**
	 * Hash un mot de passe
	 * 
	 * @param	password
	 * 			le mot de passe à hasher
	 * 
	 * @return	le mot de passe hashé sous la forme d'une chaîne de caractères
	 * 
	 * @throws	NoSuchAlgorithmException
	 * @throws	InvalidKeySpecException
	 */
	public static String hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return hash(password.toCharArray());
	}
	
	/**
	 * Vérifie un mot de passe par rapport à une chaîne de hashage
	 * 
	 * @param	password
	 * 			le mot de passe à vérifier
	 * @param	checkedHash
	 * 			la chaîne de hashage du mot de passe valide
	 * 
	 * @return	vrai si le mot de passe est correct, faux sinon
	 * 
	 * @throws	NoSuchAlgorithmException
	 * @throws	InvalidKeySpecException
	 */
	public static boolean check(String password, String checkedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return check(password.toCharArray(), checkedHash);
	}
	
	/**
	 * Vérifie un mot de passe par rapport à une chaîne de hashage
	 * 
	 * @param	password
	 * 			le mot de passe à vérifier
	 * @param	checkedHash
	 * 			la chaîne de hashage du mot de passe valide
	 * 
	 * @return	vrai si le mot de passe est correct, faux sinon
	 * 
	 * @throws	NoSuchAlgorithmException
	 * @throws	InvalidKeySpecException
	 */
	public static boolean check(char[] password, String checkedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] params = checkedHash.split(":");
		int iterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[PBKDF2_INDEX]);
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		return equals(hash, testHash);
	}
	
	/**
	 * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line 
     * system using a timing attack and then attacked off-line.
     * 
	 * @param	a
	 * 			le premier tableau d'octets
	 * @param	b
	 * 			le second tableau d'octets
	 * 
	 * @return	vrai si les deux tableaux sont égaux, faux sinon
	 */
	private static boolean equals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for(int i = 0; i < a.length && i < b.length; i++) {
			diff |= a[i] ^ b[i];
		}
		return diff == 0;
	}
	
	/**
	 * Calcule le hashage de type PBKDF2 d'un mot de passe
	 * 
	 * @param	password
	 * 			le mot de passe à hasher
	 * @param	salt
	 * 			le "sel"
	 * @param	iterations
	 * 			le compteur d'itérations (facteur de lenteur)
	 * @param	bytes
	 * 			la longueur en octets du hashage à calculer
	 * 			
	 * @return	le hashage de type PBKDF2 d'un mot de passe
	 * 
	 * @throws	NoSuchAlgorithmException
	 * @throws	InvalidKeySpecException
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes << 3);		
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}
	
	/**
	 * Converti une chaîne hexadécimale en un tableau d'octets
	 * 
	 * @param	hex
	 * 			la chaîne hexadécimale à convertir
	 *  
	 * @return	la chaîne hexadécimale décodée en tableau d'octets
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() >> 1];
		for(int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(i<<1, (i<<1)+2), HEXA_SIZE);
		}
		return binary;
	}
	
	/**
	 * Converti un tableau d'octets en une chaîne hexadécimale
	 * 
	 * @param	array
	 * 			le tableau d'octets à convertir
	 * 
	 * @return	le tableau d'octets encodé en une chaîne hexadécimale de longueur length * 2
	 */
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(HEXA_SIZE);
		int paddingLength = (array.length << 1) - hex.length();
		return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
	}

}
