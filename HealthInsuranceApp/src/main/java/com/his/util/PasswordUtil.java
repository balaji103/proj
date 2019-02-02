package com.his.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * this class is used for Encryption and decryption of pwd
 * @author Balaji
 *
 */
public class PasswordUtil {
	private static final String KEY = "aesEncryptionKey";
	private static final String INITVECTOR = "encryptionIntVec";
	static IvParameterSpec ivParameterSpec = null;
	static SecretKeySpec skeySpec = null;
	static Cipher cipher = null;
	
	/**
	 * this constructor initialize basic values
	 */
	static {
		try {
			ivParameterSpec = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
			skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
			cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * this method is gives encrypted pwd
	 * @param password
	 * @return String
	 */
	public static String encrypt(final String password) {
	    try { 
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
	        final byte[] encrypted = cipher.doFinal(password.getBytes());
	        return Base64.getEncoder().encodeToString(encrypted);
	    } catch (Exception exception) {
	    	exception.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * this method is gives decrypted pwd
	 * @param encrypted
	 * @return String
	 */
	public static String decrypt(final String encrypted) {
	    try {
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
	        final byte[] password = cipher.doFinal(encrypted.getBytes());
	        return Base64.getEncoder().encodeToString(password);
	    } catch (Exception exception) {
	    	exception.printStackTrace();
	    }
	    return null;
	}
}
