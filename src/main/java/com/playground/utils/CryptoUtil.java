package com.playground.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import com.google.common.hash.Hashing;

@Component
public class CryptoUtil {
  private static String pwd;
  private static String salt;

  private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private CryptoUtil() {}

  @Value("${CRYPTO_PWD}")
  synchronized void setPwd(String cryptoPwd) {
    pwd = cryptoPwd;
  }


  @Value("${CRYPTO_SALT}")
  synchronized void setSalt(String cryptoSalt) {
    salt = cryptoSalt;
  }


  /**
   * SHA-256 해싱
   *
   * @param plaintext - 해싱할 평문
   *
   * @return String - 해싱된 문자열
   */
  public static String encodeSha256(String plaintext) {
    return Hashing.sha256().hashString(plaintext, StandardCharsets.UTF_8).toString();
  }

  /**
   * 평문 암호화 - AES
   *
   * @param plainText - 암호화할 평문
   *
   * @return String - 암호화된 문자열
   */
  public static String encrypt(String plainText) {
    BytesEncryptor byteEncryptor = Encryptors.standard(Hex.encodeHexString(pwd.getBytes()), Hex.encodeHexString(salt.getBytes()));
    Base64EncodingTextEncryptor encryptor = new Base64EncodingTextEncryptor(byteEncryptor);

    return encryptor.encrypt(plainText);
  }

  /**
   * 평문 복호화 - AES
   *
   * @param encryptedText - 복호화할 평문
   *
   * @return String - 복호화된 문자열
   */
  public static String decrypt(String encryptedText) {
    BytesEncryptor byteEncryptor = Encryptors.standard(Hex.encodeHexString(pwd.getBytes()), Hex.encodeHexString(salt.getBytes()));
    Base64EncodingTextEncryptor encryptor = new Base64EncodingTextEncryptor(byteEncryptor);

    return encryptor.decrypt(encryptedText);
  }

  /**
   * 평문 비밀번호 해싱
   *
   * @param password - 해싱할 평문 비밀번호
   *
   * @return String - 해싱된 비밀번호
   */
  public static String encodePassword(String password) {
    return Base64.getEncoder().encodeToString(passwordEncoder.encode(password).getBytes());
  }

  /**
   * 평문 비밀번호와 해싱된 비밀번호가 같은지 검증
   *
   * @param password - 비교할 평문 비밀번호
   * @param encodedPassword - 해싱된 비밀번호
   *
   * @return boolean - 비밀번호 일치 여부
   */
  public static boolean comparePassword(String password, String encodedPassword) {
    return passwordEncoder.matches(password, new String(Base64.getDecoder().decode(encodedPassword)));
  }

  private record Base64EncodingTextEncryptor(BytesEncryptor encryptor) implements TextEncryptor {

    @Override
      public String encrypt(String text) {
        return Base64.getEncoder().encodeToString(this.encryptor.encrypt(Utf8.encode(text)));
      }

      @Override
      public String decrypt(String encryptedText) {
        return Utf8.decode(this.encryptor.decrypt(Base64.getDecoder().decode(encryptedText)));
      }
    }
}
