package com.ajax.persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

/**
 * 
 * @author majiasheng
 *
 */
@Service
public class PasswordUtility {

    // salt for added security for password hashing
    private static final String SALT = "a-salty-salt";

    /**
     * Turns user's (salted) password into a hash for storing into database
     *
     * @param userPassword
     * @return
     */
    public String getSecuredPassword(String userPassword) {

        String saltedPassword = SALT + userPassword;
        String hashedPassword = generateHashedPassword(saltedPassword);

        return hashedPassword;
    }

    /**
     * Generates a 64-byte password
     *
     * @param saltedPassword
     * @return
     */
    private String generateHashedPassword(String saltedPassword) {
        String hashedPassword = null;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha256.digest(saltedPassword.getBytes("UTF-8"));
            hashedPassword = String.format("%064x", new java.math.BigInteger(1, hashedBytes));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hashedPassword;

    }

}
