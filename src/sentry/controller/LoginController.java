package sentry.controller;


import sentry.model.Backend;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;

public class LoginController {

  private LoginPanel login;
  private MainPanel mainPanel;
  public LoginController(LoginPanel login, MainPanel mainPanel) {
    this.login = login;

    this.login.login(e -> {
      String username = this.login.getUserName();
      String password = this.login.getPassword();
      if (attemptLogin()) {
        mainPanel.showUserWebsiteAccounts(Backend.getUserWebsiteAccounts(username, password));
      }
    });
  }

  public boolean attemptLogin() {
    String username = this.login.getUserName();
    String password = this.login.getPassword();

    return Backend.isValidLogin(username, password);
  }
}
