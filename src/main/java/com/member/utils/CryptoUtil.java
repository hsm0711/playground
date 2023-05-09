package com.member.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.hash.Hashing;

public class CryptoUtil {

	public static String alg = "AES/CBC/PKCS5Padding";
	
	private static final String key = "12345678910111213";
	
	private final static String iv = key.substring(0, 16); // 16byte

	/*
	 * SHA-256 단방향 암호화 : 원래 이걸 쓰려 했는데 회원 정보 조회하는 부분에서 복호화가 안되어 아래 AES256으로 변경
	 */
	public static String sha256Endcode(String plaintext) {
		return Hashing.sha256().hashString(plaintext, StandardCharsets.UTF_8).toString();
	}

	public static String encryptAES256(String text) throws Exception {
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

		byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public static String decryptAES256(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

		byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
		byte[] decrypted = cipher.doFinal(decodedBytes);
		return new String(decrypted, "UTF-8");
	}

}
