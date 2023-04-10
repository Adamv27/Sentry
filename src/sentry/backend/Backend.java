package sentry.backend;

public class Backend {
  public static boolean isValidLogin(String username, String password) {
    username = username.replaceAll("\\s+", "");
    password = password.replaceAll("\\s+", "");

    if (username.length() == 0 || password.length() == 0) {
      return false;
    }

    return SQLite.isValidLogin(username, password);
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

  public static void main(String[] args) {
    //addNewUser("Jakel77", "hello");
    System.out.println(isValidLogin("Jakel77", "hello"));
  }
}
