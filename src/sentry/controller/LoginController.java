package sentry.controller;


import sentry.model.Backend;
import sentry.model.WebsiteAccount;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;

import java.util.ArrayList;

public class LoginController {

  private LoginPanel login;
  private MainPanel mainPanel;
  public LoginController(LoginPanel login, MainPanel mainPanel) {
    this.login = login;

    this.login.login(e -> {
      String username = this.login.getUserName();
      String password = this.login.getPassword();
      if (attemptLogin()) {
        ArrayList<WebsiteAccount> userWebsiteAccounts = Backend.getUserWebsiteAccounts(username, password);
        mainPanel.showUserWebsiteAccounts(userWebsiteAccounts);
        mainPanel.setCurrentDisplayedWebsites(userWebsiteAccounts);

      }
    });
  }

  public boolean attemptLogin() {
    String username = this.login.getUserName();
    String password = this.login.getPassword();

    return Backend.isValidLogin(username, password);
  }
}
