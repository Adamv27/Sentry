package sentry.controller;


import sentry.model.Backend;
import sentry.model.WebsiteAccount;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class LoginController {

  private final LoginPanel login;
  private final MainPanel mainPanel;
  public LoginController(LoginPanel login, MainPanel mainPanel) {
    this.login = login;
    this.mainPanel = mainPanel;

    this.login.login(e -> {
      if (attemptLogin()) {
        login();
      }
    });

    this.login.login(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          if (attemptLogin()) {
            login();
          }
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  public boolean attemptLogin() {
    String username = this.login.getUserName();
    String password = this.login.getPassword();
    return Backend.isValidLogin(username, password);
  }

  private void login() {
    ArrayList<WebsiteAccount> userWebsiteAccounts = Backend.getUserWebsiteAccounts();
    mainPanel.showUserWebsiteAccounts(userWebsiteAccounts);
    mainPanel.setCurrentDisplayedWebsites(userWebsiteAccounts);
  }
}
