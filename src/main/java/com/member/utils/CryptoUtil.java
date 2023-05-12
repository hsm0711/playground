package com.member.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.hash.Hashing;
import com.member.exception.CustomException;

import lombok.experimental.UtilityClass;

// TODO 암호화 유틸 관련다시 설계 필요
@UtilityClass
public class CryptoUtil {

	private static final String ALG = "AES/CBC/PKCS5Padding";
	private static final String KEY = "12345678910111213";
	private static final byte[] IV = KEY.substring(0, 16).getBytes(); // 16byte

	/*
	 * SHA-256 단방향 암호화
	 */
	public static String sha256Endcode(String plaintext) {
		return Hashing.sha256().hashString(plaintext, StandardCharsets.UTF_8).toString();
	}

	public static String encryptAES256(String text) {
		try {
			Cipher cipher = Cipher.getInstance(ALG);
			SecretKeySpec keySpec = new SecretKeySpec(IV, "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(IV);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

			byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			// TODO 메시지 체계 정리 후 메시징 처리 수정
			throw new CustomException("encryptAES256 에러");
		}
	}

	public static String decryptAES256(String cipherText) {
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(cipherText);

			Cipher cipher = Cipher.getInstance(ALG);
			SecretKeySpec keySpec = new SecretKeySpec(IV, "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(IV);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

			byte[] decrypted = cipher.doFinal(decodedBytes);

			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception e) {
			// TODO 메시지 체계 정리 후 메시징 처리 수정
			throw new CustomException("decryptAES256 에러");
		}
	}

}
