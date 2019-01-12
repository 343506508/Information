package com.project.tools;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;


/**
 * 3DES加密工具类
 */
public class Des3 {

	// 加解密统一使用的编码方式
	private final static String encoding = "UTF-8";

	public static void main(String[] args) throws Exception {
	}

	/**
	 * 3DES加密
	 * 
	 * @param text
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String text,String key,String iv) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(encoding));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(text.getBytes(encoding));
		//Base64编码
		return URLEncoder.encode(Base64.encode(encryptData),encoding);
	}

	/**
	 * 3DES加密
	 * 
	 * @param encodeString
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
/*	public static String encode(String encodeString, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(encodeString.getBytes(encoding));
		return Base64.encode(encryptData);
	}*/

	public static String decode(String encryptText,String key,String iv) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(encoding));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		//Base64编码
		byte[] decryptData = cipher.doFinal(Base64.decode(URLDecoder.decode(encryptText,encoding)));

		return new String(decryptData, encoding);
	}

	/*public static String decode(String encryptText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher
				.doFinal(org.apache.commons.codec.binary.Base64
						.decodeBase64(encryptText.getBytes()));
		return new String(decryptData, encoding);
	}*/
}
