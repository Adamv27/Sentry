package sentry.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel {

  JButton backButton;
  public SignUpPanel() {
    JPanel panel = new JPanel();


    panel.setPreferredSize(new Dimension(100, 100));
    backButton = new JButton("BACK");
    panel.add(backButton);

    add(panel);
  }

  public void back(ActionListener actionListener) {
    this.backButton.addActionListener(actionListener);
  }
}
