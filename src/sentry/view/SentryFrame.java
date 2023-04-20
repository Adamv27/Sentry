package sentry.view;


import sentry.controller.LoginController;
import sentry.utils.Constants;
import sentry.view.panels.LoginPanel;
import sentry.view.panels.MainPanel;
import sentry.view.panels.SignUpPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SentryFrame extends JFrame {

  private final CardLayout cardLayout;

  // PUT THESE IN CONSTANTS
  private final int WIDTH = 600;
  private final int HEIGHT = 450;

  private Point initialClick;

  public SentryFrame() {

    cardLayout = new CardLayout();
    LoginPanel loginPanel = new LoginPanel();
    SignUpPanel signUpPanel = new SignUpPanel();
    MainPanel mainPanel = new MainPanel();

    styleFrame();
    setLayout(cardLayout);

    //Initialize all controllers
    LoginController loginController = new LoginController(loginPanel, mainPanel);

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

    mainPanel.back(e -> showLoginPanel());
    signUpPanel.back(e -> showLoginPanel());



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }


  private void styleFrame() {
    // Remove default frame to create custom one
    setUndecorated(true);
    setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
        getComponentAt(initialClick);
      }
    });

    // Add a mouse motion listener to the title bar
    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int thisX = getLocation().x;
        int thisY = getLocation().y;
        int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        setLocation(X, Y);
      }
    });
  }

  private void showLoginPanel() {
    setSize(WIDTH, HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "login");
    setLocationRelativeTo(null);
  }


  private void showMainPanel() {
    setSize(Constants.MAIN_FRAME_WIDTH, Constants.MAIN_FRAME_HEIGHT);
    cardLayout.show(SentryFrame.this.getContentPane(), "stored passwords");
    setLocationRelativeTo(null);
  }
}
