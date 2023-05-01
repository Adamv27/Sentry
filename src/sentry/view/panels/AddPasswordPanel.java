package sentry.view.panels;

import sentry.utils.Constants;
import sentry.utils.VerticalFlowLayout;
import sentry.view.components.RoundJButton;
import sentry.view.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddPasswordPanel extends JPanel {

  private final JPanel panel;

  private RoundJTextField urlField;
  private RoundJTextField usernameField;
  private RoundJTextField passwordField;
  private RoundJButton addButton;

  public TitleBar titleBar;
  public AddPasswordPanel() {
    super(new BorderLayout());

    panel = new JPanel();
    panel.setBackground(Constants.PANEL_BACKGROUND);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    titleBar = new TitleBar();
    add(titleBar, BorderLayout.NORTH);

    panel.add(Box.createVerticalGlue());
    createFields();
    panel.add(Box.createVerticalGlue());

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

    urlField = new RoundJTextField(16, "URL");
    urlField.setPreferredSize(new Dimension(getWidth(), 50));
    urlField.setFont(font);
    urlField.setBackground(Constants.MIDDLE_GROUND);

    usernameField = new RoundJTextField(16, "Username");
    usernameField.setPreferredSize(new Dimension(getWidth(), 50));
    usernameField.setFont(font);
    usernameField.setBackground(Constants.MIDDLE_GROUND);

    passwordField = new RoundJTextField(16, "Password");
    passwordField.setPreferredSize(new Dimension(getWidth(), 50));
    passwordField.setFont(font);
    passwordField.setBackground(Constants.MIDDLE_GROUND);

    addButton = new RoundJButton("ADD PASSWORD");
    addButton.setPreferredSize(new Dimension(150, 50));


    fieldPanel.add(urlField);

    fieldPanel.add(usernameField);
    fieldPanel.add(passwordField);

    fieldPanel.add(addButton);

    panel.add(fieldPanel);
  }

  public String getUrl() {
    if (urlField.isEmpty()) {
      return "";
    }
    return urlField.getText();
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

  public void close(ActionListener actionListener) {
    titleBar.setOnClose(actionListener);
  }

  public void addPassword(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
  }
}
