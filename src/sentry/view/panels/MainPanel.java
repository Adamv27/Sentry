package sentry.view.panels;


import sentry.model.WebsiteAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {

  JButton backButton;
  public MainPanel() {
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(100, 100));
    backButton = new JButton("BACK");
    panel.add(backButton);

    add(panel);
  }

  public void back(ActionListener actionListener) {
    this.backButton.addActionListener(actionListener);
  }

  public void showUserWebsiteAccounts(ArrayList<WebsiteAccount> userWebsiteAccounts) {
  }
}
