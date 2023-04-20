package sentry.model;

public class WebsiteAccount {
  private String url;
  private String username;
  private String password;

  public WebsiteAccount(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String toString() {
    return "URL: " + this.url + " Username: " + this.username + " Password: " + this.password;
  }
}
