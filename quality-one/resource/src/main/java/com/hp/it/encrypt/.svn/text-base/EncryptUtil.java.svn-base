package com.hp.it.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class EncryptUtil {
	byte[] encryptKey;
	DESedeKeySpec spec;
	SecretKeyFactory keyFactory;
	SecretKey theKey;
	Cipher cipher;
	IvParameterSpec IvParameters;

	public EncryptUtil() {
		try {
			try {
				Cipher c = Cipher.getInstance("DESede");
			} catch (Exception e) {
				System.err.println("Installling SunJCE provider.");
				Provider sunjce = new com.sun.crypto.provider.SunJCE();
				Security.addProvider(sunjce);
			}
			encryptKey = "This is a test DESede Key".getBytes();

			// spec = new DESedeKeySpec(encryptKey);
			//
			// keyFactory = SecretKeyFactory.getInstance("DESede");
			//
			// theKey = keyFactory.generateSecret(spec);

			KeyGenerator generator = KeyGenerator.getInstance("DESede");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(encryptKey);
			generator.init(secureRandom);
			theKey = generator.generateKey();

			cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

			IvParameters = new IvParameterSpec(new byte[] { 12, 34, 56, 78, 90, 87, 65, 43 });
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}

	public String encrypt(String password) {
		String encrypted_password = null;
		byte[] encrypted_pwd = null;

		try {
			/*cipher.init(Cipher.ENCRYPT_MODE, theKey, IvParameters);
			byte[] plainttext = password.getBytes();
			encrypted_pwd = cipher.doFinal(plainttext);*/
			byte[] plainttext = password.getBytes();
			for(int i =0 ; i< plainttext.length ; i++)
			{
				plainttext[i] = (byte) (plainttext[i] + 5) ;
			}
			encrypted_password = new String(plainttext);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return encrypted_password;
	}

	public String decrypt(String password) {
		String decrypted_password = null;
		try {
			/*cipher.init(Cipher.DECRYPT_MODE, theKey, IvParameters);
			byte[] decryptedPassword = password.getBytes();
			byte[] decrypted_pwd = cipher.doFinal(decryptedPassword);
			decrypted_password = new String(decrypted_pwd);*/
			
			byte[] plainttext = password.getBytes();
			for(int i =0 ; i< plainttext.length ; i++)
			{
				plainttext[i] = (byte) (plainttext[i] - 5);
			}
			decrypted_password= new String(plainttext);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return decrypted_password;
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		EncryptUtil encryptUtil = new EncryptUtil();
		String encry = encryptUtil.encrypt("@@999");
		String dec = new EncryptUtil().decrypt(encry);
		System.out.println(dec);
	}
}
