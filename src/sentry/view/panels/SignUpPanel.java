package sentry.view.panels;

import sentry.utils.Constants;
import sentry.utils.VerticalFlowLayout;
import sentry.view.components.RoundJButton;
import sentry.view.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel {
  private final TitleBar titleBar;
  private final JPanel panel;

  private RoundJTextField usernameField;
  private RoundJTextField passwordField;
  private RoundJTextField confirmPasswordField;
  private RoundJButton signupButton;

  private JButton backButton;


  public SignUpPanel() {
    super(new BorderLayout());

    panel = new JPanel();
    panel.setBackground(Constants.PANEL_BACKGROUND);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    //addStyle();

    titleBar = new TitleBar();
    add(titleBar, BorderLayout.NORTH);


    panel.add(Box.createVerticalGlue());
    //panel.add(Box.createVerticalStrut(25));
    createFields();

    panel.add(Box.createVerticalGlue());
//    panel.add(Box.createVerticalStrut(38));
//

    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    add(panel, BorderLayout.CENTER);
  }

  private void createFields() {
    JPanel fieldPanel = new JPanel();
    fieldPanel.setBackground(Constants.PANEL_BACKGROUND);
    VerticalFlowLayout verticalFlowLayout = new VerticalFlowLayout(VerticalFlowLayout.CENTER, VerticalFlowLayout.TOP);
    verticalFlowLayout.setVgap(15);
    fieldPanel.setLayout(verticalFlowLayout);

    Font font = Constants.TEXT_FONT.deriveFont(Font.PLAIN, 18);

    usernameField = new RoundJTextField(16, "Username");
    usernameField.setPreferredSize(new Dimension(getWidth(), 50));
    usernameField.setFont(font);
    usernameField.setBackground(Constants.MIDDLE_GROUND);

    passwordField = new RoundJTextField(16, "Password");
    passwordField.hideText(true);
    passwordField.setPreferredSize(new Dimension(getWidth(), 50));
    passwordField.setFont(font);
    passwordField.setBackground(Constants.MIDDLE_GROUND);

    confirmPasswordField = new RoundJTextField(16, "Confirm Password");
    confirmPasswordField.hideText(true);
    confirmPasswordField.setPreferredSize(new Dimension(getWidth(), 50));
    confirmPasswordField.setFont(font);
    confirmPasswordField.setBackground(Constants.MIDDLE_GROUND);

    signupButton = new RoundJButton("SIGN UP");
    signupButton.setFont(font);
    signupButton.setPreferredSize(new Dimension(150, 50));


    fieldPanel.add(usernameField);
    fieldPanel.add(passwordField);
    fieldPanel.add(confirmPasswordField);
    fieldPanel.add(signupButton);

    panel.add(fieldPanel);
  }

  public String getUsername() {
    if (usernameField.isEmpty()) {
      return "";
    }
    return usernameField.getText();
  }
  public String getPassword() {
    if (passwordField.isEmpty()) {
      return "";
    }
    return passwordField.getText();
  }

  public String getConfirmedPassword() {
    if (confirmPasswordField.isEmpty()) {
      return "";
    }
    return confirmPasswordField.getText();
  }

  public void close(ActionListener actionListener) {
    titleBar.setOnClose(actionListener);
  }

  public void back(ActionListener actionListener) {
    this.backButton.addActionListener(actionListener);
  }

  public void signup(ActionListener actionListener) {
    this.signupButton.addActionListener(actionListener);
  }
}

