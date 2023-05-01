package sentry.controller;

import sentry.model.Backend;
import sentry.view.frames.SentryFrame;
import sentry.view.panels.SignUpPanel;

public class SignUpController {
  public SignUpController(SentryFrame frame, SignUpPanel signUpPanel) {
    signUpPanel.signup(e -> {
      String username = signUpPanel.getUsername();
      String password = signUpPanel.getPassword();
      String confirmedPassword = signUpPanel.getConfirmedPassword();
      if (Backend.isValidCredentials(username, password, confirmedPassword)) {
        System.out.println("Signing up username: " + username + " password: " + password);
        Backend.addNewUser(username, password);
        frame.showLoginPanel();
      }
    });
  }
}
