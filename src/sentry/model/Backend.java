package sentry.model;

import sentry.utils.Encryptor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Backend {
  private static String currentLoggedInUser;
  public static boolean isValidLogin(String username, String password) {
    username = username.replaceAll("\\s+", "");
    password = password.replaceAll("\\s+", "");

    if (username.length() == 0 || password.length() == 0) {
      return false;
    }

    if (SQLite.isValidLogin(username, password)) {
      login(Encryptor.hash(username, password));
      return true;
    }
    return false;
  }

  private static void login(String userID) {
    currentLoggedInUser = userID;
  }

  public static boolean deletePassword(WebsiteAccount account) {
    if (currentLoggedInUser == null || account == null) {
      return false;
    }
    return SQLite.deletePassword(currentLoggedInUser, account);
  }

  public static boolean isValidCredentials(String username, String password, String confirmedPassword) {
    if (SQLite.userExists(username)) {
      System.out.println("This username is already taken");
      return false;
    }
    return password.equals(confirmedPassword) && username.length() > 3 && password.length() > 3;
  }

  private void logout() {
    currentLoggedInUser = null;
  }

  public static void addNewUser(String username, String password) {
    SQLite.addNewUser(username, password);
  }


  public static ArrayList<WebsiteAccount> getUserWebsiteAccounts() {
    ArrayList<WebsiteAccount> websiteAccounts = new ArrayList<>();

    if (currentLoggedInUser != null) {
      websiteAccounts = SQLite.getUserWebsiteAccounts(currentLoggedInUser);
    }
    return websiteAccounts;
  }

  public static boolean addNewPassword(WebsiteAccount websiteAccount) {
    if (currentLoggedInUser == null) {
         return false;
    }
    return SQLite.addNewPassword(currentLoggedInUser, websiteAccount);
  }

  public static BufferedImage getUrlLogo(String url) {
    try {
      URL response = new URL("https://logo.clearbit.com/" + url);
      return ImageIO.read(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args) {
    //addNewUser("Adam", "12345");
    //System.out.println(isValidLogin("Adam", "12345"));
  }
}
