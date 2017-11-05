package com.ajax.dbm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author majiasheng
 *
 */
public class PasswordUtility {
	
	// salt for added security for password hashing
	private static final String SALT = "a-salty-salt";
	
	/**
	 * Turns user's (salted) password into a hash for storing into database 
	 * @param userPassword
	 * @return
	 */
	public static String getSecuredPassword(String userPassword) {
		
		String saltedPassword = SALT + userPassword;
		String hashedPassword = generateHashedPassword(saltedPassword);
		
		return hashedPassword;
	}

	/**
	 * Generates a 64-byte password
	 * @param saltedPassword
	 * @return
	 */
	private static String generateHashedPassword(String saltedPassword) {
		String hashedPassword = null;
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = sha256.digest(saltedPassword.getBytes("UTF-8"));
			hashedPassword = String.format("%064x", new java.math.BigInteger(1, hashedBytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		
		return hashedPassword;
		
	}
	
	/**
	 * Checks if user's password entered after hashing matches with the 
	 * 	hashed password from database. 
	 * @param userPassword
	 * @param hashedPassword
	 * @return
	 */
	public static boolean isPasswordMatch(String userPassword, String hashedPassword) {
		return hashedPassword.equals(getSecuredPassword(userPassword));
	}
}
