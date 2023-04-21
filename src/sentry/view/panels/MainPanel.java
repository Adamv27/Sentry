package sentry.view.panels;


import sentry.model.WebsiteAccount;
import sentry.utils.Constants;
import sentry.utils.VerticalFlowLayout;
import sentry.view.components.RoundJButton;
import sentry.view.components.RoundJPanel;
import sentry.view.components.RoundJTextField;
import sentry.view.components.ScrollingDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {

  private JPanel dataPanel;
  private JPanel displayPanel;

  private ScrollingDisplay scrollingDisplay;
  private JPanel passwordPanel;

  private ArrayList<WebsiteAccount> currentDisplayedWebsites;

  private RoundJTextField searchBar;
  public MainPanel() {
    super(new BorderLayout());

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
    dataPanelContainer.setMargins(20);
    dataPanelContainer.setBackground(Constants.MIDDLE_GROUND);

    JPanel searchBar = createSearchBar(dataPanelContainer);
    JPanel passwordDisplay = createPasswordDisplay(dataPanelContainer);

    dataPanelContainer.add(searchBar, BorderLayout.NORTH);
    dataPanelContainer.add(passwordDisplay, BorderLayout.CENTER);

    dataPanel.add(dataPanelContainer, BorderLayout.CENTER);
  }

  private JPanel createSearchBar(JPanel container) {
    //Top center container to hold search bar and add button
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
    searchPanel.add(Box.createHorizontalGlue());
    searchPanel.add(Box.createHorizontalStrut(25));
    searchPanel.setPreferredSize(new Dimension(container.getWidth(), 50));
    searchPanel.setBackground(Constants.MIDDLE_GROUND);

    // Actual search bar
    searchBar = new RoundJTextField(8);
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

    return searchPanel;
  }

  private JPanel createPasswordDisplay(JPanel container) {
    scrollingDisplay = new ScrollingDisplay(container);
    return scrollingDisplay;
  }


  public void search(ActionListener actionListener) {
    this.searchBar.addActionListener(actionListener);
  }

  public void search(KeyListener keyListener) {
    this.searchBar.addKeyListener(keyListener);
  }

  public String getSearchText() {
    return this.searchBar.getText();
  }




  public void showUserWebsiteAccounts(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    scrollingDisplay.update(userWebsiteAccounts);
    revalidate();
  }

  public ArrayList<WebsiteAccount> getCurrentDisplayedWebsites() {
    return this.currentDisplayedWebsites;
  }

  public void setCurrentDisplayedWebsites(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    this.currentDisplayedWebsites = userWebsiteAccounts;
  }
}
