package sentry.view.frames;


import sentry.controller.LoginController;
import sentry.controller.MainPageController;
import sentry.controller.SignUpController;
import sentry.utils.Constants;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;
import sentry.view.panels.SignUpPanel;

import javax.swing.*;
import java.awt.*;


public class SentryFrame extends StyledFrame {

  private final CardLayout cardLayout;

  public SentryFrame() {

    cardLayout = new CardLayout();
    LoginPanel loginPanel = new LoginPanel();
    SignUpPanel signUpPanel = new SignUpPanel();
    MainPanel mainPanel = new MainPanel();

    setPreferredSize(new Dimension(Constants.LOGIN_FRAME_WIDTH, Constants.LOGIN_FRAME_HEIGHT));
    setLayout(cardLayout);

    //Initialize all controllers
    LoginController loginController = new LoginController(loginPanel, mainPanel);
    MainPageController mainPageController = new MainPageController(mainPanel);
    new SignUpController(this, signUpPanel);

    // Add each view panel to card layout
    add(loginPanel, "login");
    add(signUpPanel, "sign up");
    add(mainPanel, "stored passwords");

    loginPanel.login(e -> {
      if (loginController.attemptLogin()) {
        showMainPanel();
      } else {
        System.out.println("Invalid Login!");
      }
    });

    loginPanel.signUp(e -> cardLayout.show(SentryFrame.this.getContentPane(), "sign up"));

    //signUpPanel.back(e -> showLoginPanel());
    signUpPanel.close(e-> showLoginPanel());


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void showLoginPanel() {
    setSize(Constants.LOGIN_FRAME_WIDTH, Constants.LOGIN_FRAME_HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "login");
    setLocationRelativeTo(null);
  }


  public void showMainPanel() {
    setSize(Constants.MAIN_FRAME_WIDTH, Constants.MAIN_FRAME_HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "stored passwords");
    setLocationRelativeTo(null);
  }
}
