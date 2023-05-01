package sentry.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor {
  public static String hash(String username, String password) {
    return Hashing.sha256()
            .hashString(username + password, StandardCharsets.UTF_8)
            .toString();
  }

  public static String encrypt(String password) {
    byte[] encryptArray = Base64.getEncoder().encode(password.getBytes());
    return new String(encryptArray, StandardCharsets.UTF_8);
  }


  public static String decrypt(String encryption) {
    byte[] decryptArray = Base64.getDecoder().decode(encryption.getBytes());
    return new String(decryptArray, StandardCharsets.UTF_8);
  }
}
