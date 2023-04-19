package sentry.controller;

import sentry.model.Backend;
import sentry.view.panels.LoginPanel;

public class LoginController {

  private LoginPanel login;
  public LoginController(LoginPanel login) {
    this.login = login;
  }

  public boolean attemptLogin() {
    String username = this.login.getUserName();
    String password = this.login.getPassword();

    return Backend.isValidLogin(username, password);
  }
}
