package sentry.backend;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
  private static final byte[] SALT = "5937612501".getBytes();
  private static final int ITERATION_COUNT = 10000;
  private static final int KEY_LENGTH = 128;

  public static String hash(String username, String password) {
    return Hashing.sha256()
            .hashString(username + password, StandardCharsets.UTF_8)
            .toString();
  }

  public static String encryptPassword(String password) {
    return password;
  }


  public static String decryptPassword(String encryption) {
    return encryption;
  }


  public static void main(String[] args) {
  }
}
