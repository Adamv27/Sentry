package sentry.view.panels;


import sentry.model.WebsiteAccount;
import sentry.utils.Constants;
import sentry.utils.VerticalFlowLayout;
import sentry.view.components.RoundJButton;
import sentry.view.components.RoundJPanel;
import sentry.view.components.RoundJTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {

  JButton backButton;

  private JPanel dataPanel;
  private JPanel displayPanel;
  public MainPanel() {
    super(new BorderLayout());
//    JPanel panel = new JPanel();
//    panel.setPreferredSize(new Dimension(100, 100));
      backButton = new JButton("BACK");
//
//    add(panel);

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));


    displayPanel = new JPanel(new BorderLayout());
    displayPanel.setBackground(Constants.PANEL_BACKGROUND);

    createDataPanel();

    contentPanel.add(dataPanel);
    contentPanel.add(displayPanel);

    add(contentPanel, BorderLayout.CENTER);

    add(new TitleBar(), BorderLayout.NORTH);
  }

  public void createDataPanel() {

    // Entire Left side of the application
    dataPanel = new JPanel(new BorderLayout());
    dataPanel.setBackground(Constants.PANEL_BACKGROUND);
    dataPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

    // Center left container to hold data and search bar
    RoundJPanel dataPanelContainer = new RoundJPanel(25);
    dataPanelContainer.setLayout(new BorderLayout());
    //JPanel dataPanelContainer = new JPanel(new BorderLayout());
    dataPanelContainer.setMargins(20);
    dataPanelContainer.setBackground(Constants.MIDDLE_GROUND);

    //Top center container to hold search bar and add button
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
    searchPanel.add(Box.createHorizontalGlue());
    searchPanel.add(Box.createHorizontalStrut(25));
    searchPanel.setPreferredSize(new Dimension(dataPanelContainer.getWidth(), 50));
    searchPanel.setBackground(Constants.MIDDLE_GROUND);

    // Actual search bar
    RoundJTextField searchBar = new RoundJTextField(8);
    searchBar.setCaretColor(new Color(0x454545));
    searchBar.setPreferredSize(new Dimension(50, 50));
    searchPanel.add(searchBar);
    Font font = Constants.TEXT_FONT.deriveFont(Font.PLAIN, 18);
    searchBar.setFont(font);

    searchPanel.add(Box.createHorizontalStrut(5));

    // Button to add new passwords
    JButton addButton = new RoundJButton("+");
    addButton.setFont(Constants.TEXT_FONT.deriveFont(Font.BOLD, 25));
    searchPanel.add(addButton);
    searchPanel.add(Box.createHorizontalGlue());

    // Bottom center container to hold scroll list of all websites and their passwords
    JPanel passwordPanel = new JPanel(new BorderLayout());
    passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    passwordPanel.setBackground(Constants.MIDDLE_GROUND);


    dataPanelContainer.add(searchPanel, BorderLayout.NORTH);
    dataPanelContainer.add(passwordPanel, BorderLayout.CENTER);

    dataPanel.add(dataPanelContainer, BorderLayout.CENTER);
  }

  public void back(ActionListener actionListener) {
    this.backButton.addActionListener(actionListener);
  }


  public void showUserWebsiteAccounts(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    for (WebsiteAccount websiteAccount : userWebsiteAccounts) {
      System.out.println(websiteAccount);
    }
  }
}
