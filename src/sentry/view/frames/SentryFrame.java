package sentry.view.frames;


import sentry.controller.LoginController;
import sentry.controller.MainPageController;
import sentry.utils.Constants;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;
import sentry.view.panels.SignUpPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class SentryFrame extends StyledFrame {

  private final CardLayout cardLayout;

  // PUT THESE IN CONSTANTS
  private final int WIDTH = 600;
  private final int HEIGHT = 450;

  public SentryFrame() {

    cardLayout = new CardLayout();
    LoginPanel loginPanel = new LoginPanel();
    SignUpPanel signUpPanel = new SignUpPanel();
    MainPanel mainPanel = new MainPanel();

    setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
    setLayout(cardLayout);

    //Initialize all controllers
    LoginController loginController = new LoginController(loginPanel, mainPanel);
    MainPageController mainPageController = new MainPageController(mainPanel);
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

    signUpPanel.back(e -> showLoginPanel());



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void showLoginPanel() {
    setSize(WIDTH, HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "login");
    setLocationRelativeTo(null);
  }


  public void showMainPanel() {
    setSize(Constants.MAIN_FRAME_WIDTH, Constants.MAIN_FRAME_HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "stored passwords");
    setLocationRelativeTo(null);
  }
}
