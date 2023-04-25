package sentry.model;

import sentry.utils.Encryptor;

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

  private void logout() {
    currentLoggedInUser = null;
  }

  public static boolean addNewUser(String username, String password) {
    if (SQLite.userExists(username)) {
      System.out.println("This username is already taken");
      return false;
    }
    SQLite.addNewUser(username, password);
    System.out.println("User: " + username + " has been added.");
    return true;
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

  public static void main(String[] args) {
    //addNewUser("Adam", "12345");
    //System.out.println(isValidLogin("Adam", "12345"));
  }
}
