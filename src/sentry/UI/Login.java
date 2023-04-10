package sentry.UI;

import sentry.UI.components.CloseButton;
import sentry.UI.components.RoundJButton;
import sentry.UI.components.RoundJPasswordField;
import sentry.UI.components.RoundJTextField;
import sentry.backend.Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.*;

public class Login {
  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  JFrame frame = new JFrame();

  JLabel title;
  JLabel signUpText;

  JButton loginButton = new RoundJButton("LOGIN");
  JButton signupButton = new JButton("SIGN UP");

  RoundJPasswordField passwordTextField;
  RoundJTextField usernameTextField;

  Font textFont = ResourceLoader.getFont("cuyabra Regular");

  private Point initialClick;

  public Login() {


    styleFrame();
    registerFonts();

    createTitle();
    createUsernameField();
    createPasswordField();
    createSignUpText();

    loginButton.setBounds(200, 260, 200, 50);
    loginButton.setFont(textFont);
    loginButton.setFocusable(true);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.addActionListener(e -> attemptLogin());


    frame.add(title);
    frame.add(usernameTextField);
    frame.add(passwordTextField);
    frame.add(loginButton);


    CloseButton closeButton = new CloseButton("X", frame);
    closeButton.setBounds(550, 0, 50, 35);
    frame.add(closeButton);

    frame.add(signUpText);
    frame.add(signupButton);

    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  private void registerFonts() {
    System.out.println(ResourceLoader.getFontNames());

    for (String fontName : ResourceLoader.getFontNames()) {
      ge.registerFont(ResourceLoader.getFont(fontName));
    }
  }

  private void styleFrame() {
    frame.setLayout(null);
    frame.setUndecorated(true);
    frame.setSize(600, 450);
    frame.getContentPane().setBackground(Constants.DARK_GREY);
    frame.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
        frame.getComponentAt(initialClick);
      }
    });

    // Add a mouse motion listener to the title bar
    frame.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int thisX = frame.getLocation().x;
        int thisY = frame.getLocation().y;
        int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        frame.setLocation(X, Y);
      }
    });
    JPanel titleBar = new JPanel(new BorderLayout());
    titleBar.setBackground(Color.GRAY);
    titleBar.setPreferredSize(new Dimension(frame.getWidth(), 30));
  }

  private void attemptLogin() {
    String username = usernameTextField.getText();
    String password = new String(passwordTextField.getPassword());

    System.out.println(username);
    System.out.println(password);

    if (Backend.isValidLogin(username, password)) {
      System.out.println();
      System.out.println("Successfully logged in");
      System.out.println("Username: " + username);
      System.out.println("Password: " + password);
      System.out.println();
    }
  }

  private void createTitle() {
    title = new JLabel("SENTRY");
    title.setForeground(Color.GREEN);
    title.setBounds(100, 50, 400, 50);
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setFont(ResourceLoader.getFont("Bord Demo"));
  }
  private void createUsernameField() {
    usernameTextField = new RoundJTextField(25);
    Font font = textFont.deriveFont(Font.PLAIN, 18);
    usernameTextField.setFont(font);
    usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
    usernameTextField.setBounds(100, 125, 400, 50);
  }

  private void createPasswordField() {
    passwordTextField = new RoundJPasswordField(25);
    passwordTextField.setFont(textFont);
    passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordTextField.setBounds(100, 190, 400, 50);

    passwordTextField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          attemptLogin();
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

  private void createSignUpText() {
    signUpText = new JLabel("Don't have an account yet?");
    signUpText.setForeground(Color.lightGray);
    signUpText.setBounds(60, 325, 400, 25);
    signUpText.setHorizontalAlignment(SwingConstants.CENTER);


    signupButton = new JButton("Sign Up");
    signupButton.setBackground(Constants.DARK_GREY);
    signupButton.setBorder(null);
    signupButton.setFocusPainted(false);
    //setOpaque(false);
    signupButton.setFocusable(false);
    signupButton.setForeground(Constants.LIGHT_GREEN);
    signupButton.setBounds(340, 323, 50, 25);
    //signUpText.setHorizontalAlignment(SwingConstants.CENTER);

    Font font = textFont.deriveFont(Font.PLAIN, 12);
    signUpText.setFont(font);
  }
}
