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

  private CardLayout cardLayout;

  private int WIDTH = 600;
  private int HEIGHT = 450;

  private Point initialClick;
  public SentryFrame() {
    cardLayout = new CardLayout();
    LoginPanel loginPanel = new LoginPanel();
    SignUpPanel signUpPanel = new SignUpPanel();
    MainPanel mainPanel = new MainPanel();

    setLayout(cardLayout);

    //Initialize all controllers
    LoginController loginController = new LoginController(loginPanel);

    // Add each view panel to card layout
    add(loginPanel, "login");
    add(signUpPanel, "sign up");
    add(mainPanel, "stored passwords");

    loginPanel.login(e -> {
      if (loginController.attemptLogin()) {
        cardLayout.show(SentryFrame.this.getContentPane(), "stored passwords");
      } else {
        System.out.println("Invalid Login!");
      }
    });

    loginPanel.signUp(e -> cardLayout.show(SentryFrame.this.getContentPane(), "sign up"));


    styleFrame();

    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  private void styleFrame() {
    setUndecorated(true);
    setSize(this.WIDTH, this.HEIGHT);
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
}
