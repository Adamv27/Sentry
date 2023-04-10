package sentry.backend;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.sql.*;

public class SQLite {

  private static final String URL = "jdbc:sqlite:src/sentry/db/";

  public static void setup() {
    createNewDatabase("users.db");
    createNewDatabase("websites.db");
    createTables();
  }

  private static void createNewDatabase(String fileName) {
    File file = new File("src/sentry/db/" + fileName);
    if (file.exists()) {
      System.out.println("A database with this name already exists");
      return;
    }

    String url = URL + fileName;
    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        System.out.printf("DataBase: %s has been created%n", fileName);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void createTables() {
    String usersSQL = "CREATE TABLE IF NOT EXISTS users (\n"
                    + "   username text NOT NULL,\n"
                    + "   user_id text PRIMARY KEY\n"
                    + ");";

    String websitesSQL = "CREATE TABLE IF NOT EXISTS websites (\n"
                       + "   user_id text PRIMARY KEY,\n"
                       + "   url text NOT NULL,\n"
                       + "   username text NOT NULL,\n"
                       + "   password text NOT NULL\n"
                       + ");";

    try (Connection conn = DriverManager.getConnection(URL + "users.db");
         Statement stmt = conn.createStatement()) {
      stmt.execute(usersSQL);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    try (Connection conn = DriverManager.getConnection(URL + "websites.db");
         Statement stmt = conn.createStatement()) {
      stmt.execute(websitesSQL);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static boolean addNewUser(String username, String masterPassword) {
    String user_id = Encryptor.hash(username, masterPassword);

    String sql = "INSERT INTO users(username, user_id) VALUES (?, ?)";
    try (Connection conn = DriverManager.getConnection(URL + "users.db")) {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, user_id);
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public static boolean addNewPassword(String userID, WebsiteAccount websiteAccount) {
    String sql = "INSERT INTO websites(user_id, url, username, password) VALUES (?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(URL)) {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, userID);
      stmt.setString(2, websiteAccount.getUrl());
      stmt.setString(3, websiteAccount.getUsername());
      stmt.setString(4, Encryptor.encryptPassword(websiteAccount.getPassword()));

      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public static boolean userExists(String username) {
    try (Connection conn = DriverManager.getConnection(URL + "users.db")) {
      PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users WHERE username=?");

      stmt.setString(1, username);
      ResultSet resultSet = stmt.executeQuery();

      if (resultSet == null || resultSet.getString("username") == null) {
        return false;
      }

      return resultSet.getString("username").equals(username);

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public static boolean isValidLogin(String username, String password) {
    String user_id = Encryptor.hash(username, password);

    try (Connection conn = DriverManager.getConnection(URL + "users.db")) {
      PreparedStatement stmt = conn.prepareStatement("SELECT username, user_id FROM users WHERE username=?");

      stmt.setString(1, username);
      ResultSet resultSet = stmt.executeQuery();

      if (resultSet == null) {
        return false;
      }
      if (resultSet.getString("username") == null || resultSet.getString("user_id") == null) {
        return false;
      }

      return resultSet.getString("username").equals(username)
              && resultSet.getString("user_id").equals(user_id);

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

  }

  public static void printAllData() {
    try (Connection conn = DriverManager.getConnection(URL + "users.db")) {
      Statement stmt = conn.createStatement();

      ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");

      System.out.println();
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
        System.out.println();
      }
      System.out.println();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
  }
}
