package sentry.view.panels;

import sentry.utils.Constants;
import sentry.utils.VerticalFlowLayout;
import sentry.view.components.RoundJButton;
import sentry.view.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel {
  private final JPanel panel;

  private RoundJTextField usernameTextField;
  private RoundJTextField passwordField;

  private JButton loginButton;
  private JButton signupButton;
  public LoginPanel() {
    super(new BorderLayout());

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    addStyle();

    TitleBar titleBar = new TitleBar();
    titleBar.setOnClose(e -> System.exit(0));
    add(titleBar, BorderLayout.NORTH);

    createTitle();
    createFields();
    createLoginButton();
    createSignUp();

    panel.add(Box.createVerticalGlue());
    panel.add(Box.createVerticalStrut(38));
    panel.add(Box.createVerticalGlue());

    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    add(panel, BorderLayout.CENTER);

  }

  public void signUp(ActionListener actionListener) {
    signupButton.addActionListener(actionListener);
  }

  public void login(ActionListener actionListener) {
    loginButton.addActionListener(actionListener);
  }

  public void login(KeyListener keyListener) {
    this.usernameTextField.addKeyListener(keyListener);
    this.passwordField.addKeyListener(keyListener);
  }
  public String getUserName() {
    String text = this.usernameTextField.getText();
    if (text.equals(usernameTextField.getPlaceholder())) {
      return "";
    }
    return text;
  }

  public String getPassword() {
    String text = this.passwordField.getText();
    if (text.equals(passwordField.getPlaceholder())) {
      return "";
    }
    return text;
  }

  private void addStyle() {
    setBackground(Constants.PANEL_BACKGROUND);

    panel.setBackground(Constants.PANEL_BACKGROUND);
  }

  private void createTitle() {
    JLabel title = new JLabel("SENTRY");
    title.setForeground(Color.GREEN);
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setFont(Constants.TITLE_FONT);

    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    titlePanel.setPreferredSize(new Dimension(getWidth(), (int) title.getPreferredSize().getHeight()));
    titlePanel.add(title);

    titlePanel.setBackground(Constants.PANEL_BACKGROUND);
    panel.add(titlePanel);
  }

  private void createFields() {
    JPanel fieldPanel = new JPanel();
    VerticalFlowLayout verticalFlowLayout = new VerticalFlowLayout(VerticalFlowLayout.CENTER, VerticalFlowLayout.TOP);
    verticalFlowLayout.setVgap(10);
    fieldPanel.setLayout(verticalFlowLayout);


    usernameTextField = new RoundJTextField(22, "Username");
    Font font = Constants.TEXT_FONT.deriveFont(Font.PLAIN, 18);
    usernameTextField.setFont(font);
    usernameTextField.setBackground(Constants.MIDDLE_GROUND);
    usernameTextField.setPreferredSize(new Dimension(usernameTextField.getWidth(), 50));

    usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
    fieldPanel.add(usernameTextField);


    passwordField = new RoundJTextField(22, "Password");
    passwordField.hideText(true);
    passwordField.setFont(font);
    passwordField.setBackground(Constants.MIDDLE_GROUND);
    passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordField.setPreferredSize(new Dimension(usernameTextField.getWidth(), 50));
    fieldPanel.add(passwordField);

    fieldPanel.setPreferredSize(new Dimension(getWidth(), 100));
    fieldPanel.setBackground(Constants.PANEL_BACKGROUND);

    panel.add(fieldPanel);
  }

  private void createLoginButton() {
    JPanel buttonPanel = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.CENTER, VerticalFlowLayout.TOP));

    loginButton = new RoundJButton("LOGIN");
    loginButton.setPreferredSize(new Dimension(200, 50));
    loginButton.setFont(Constants.TEXT_FONT);
    loginButton.setFocusable(true);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    buttonPanel.setPreferredSize(new Dimension(getWidth(), (int) loginButton.getPreferredSize().getHeight() - 15));
    buttonPanel.add(loginButton);

    buttonPanel.setBackground(Constants.PANEL_BACKGROUND);

    panel.add(buttonPanel);
  }

  private void createSignUp() {
    JPanel signupPanel = new JPanel();

    signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.X_AXIS));
    signupPanel.add(Box.createHorizontalGlue());

    JLabel signUpText = new JLabel("Don't have an account yet?");
    signUpText.setAlignmentX(Component.CENTER_ALIGNMENT);
    signUpText.setForeground(Color.lightGray);
    signUpText.setPreferredSize(new Dimension((int) signUpText.getPreferredSize().getWidth() + 1, 25));
    Font font = Constants.TEXT_FONT.deriveFont(Font.PLAIN, 12);
    signUpText.setFont(font);

    signupButton = new JButton("Sign Up");
    signupButton.setBackground(Constants.PANEL_BACKGROUND);
    signupButton.setBorder(null);
    signupButton.setFocusPainted(false);
    signupButton.setContentAreaFilled(false);
    signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    signupButton.setAlignmentY( (Component.BOTTOM_ALIGNMENT - 0.35f));
    //setOpaque(false);
    signupButton.setFocusable(false);
    signupButton.setForeground(Constants.LIGHT_GREEN);
    signupButton.setPreferredSize(new Dimension(50, 25));

    signupPanel.add(signUpText);
    signupPanel.add(Box.createHorizontalStrut(5));
    signupPanel.add(signupButton);
    signupPanel.add(Box.createHorizontalGlue());

    signupPanel.setBackground(Constants.PANEL_BACKGROUND);

    panel.add(signupPanel);

  }
}
