package sentry.view.panels;


import sentry.controller.MainPageController;
import sentry.model.Backend;
import sentry.model.WebsiteAccount;
import sentry.utils.Constants;

import sentry.utils.ResourceLoader;
import sentry.view.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class MainPanel extends JPanel {

  private JPanel dataPanel;
  private RoundJTextField searchBar;
  private JButton addButton;
  private ScrollingDisplay scrollingDisplay;

  private JPanel displayPanel;
  private RoundJPanel displayPanelContainer;
  private IconButton deleteButton;

  private ArrayList<WebsiteAccount> currentDisplayedWebsites;

  private WebsiteAccount currentlyDisplayedWebsite;

  public MainPanel() {
    super(new BorderLayout());

    TitleBar titleBar = new TitleBar();
    titleBar.setOnClose(e -> System.exit(0));
    add(titleBar, BorderLayout.NORTH);

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));


    createDataPanel();
    createDisplayPanel();

    contentPanel.add(dataPanel);
    contentPanel.add(displayPanel);

    add(contentPanel, BorderLayout.CENTER);
  }

  private void createDisplayPanel() {
    displayPanel = new JPanel(new BorderLayout());
    displayPanel.setBackground(Constants.PANEL_BACKGROUND);
    displayPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

    displayPanelContainer = new RoundJPanel(25);
    displayPanelContainer.setLayout(new BorderLayout());
    displayPanelContainer.setMargins(20);
    displayPanelContainer.setBackground(Constants.MIDDLE_GROUND);

    displayPanel.add(displayPanelContainer, BorderLayout.CENTER);
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
    searchBar = new RoundJTextField(8, "Search");
    searchBar.setCaretColor(new Color(0x454545));
    searchBar.setPreferredSize(new Dimension(50, 50));
    searchPanel.add(searchBar);
    Font font = Constants.TEXT_FONT.deriveFont(Font.PLAIN, 18);
    searchBar.setFont(font);

    searchPanel.add(Box.createHorizontalStrut(5));

    // Button to add new passwords
    addButton = new RoundJButton("+");
    addButton.setFocusPainted(false);
    addButton.setContentAreaFilled(false);
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


  public void showData(MouseListener mouseListener) {
    this.scrollingDisplay.showData(mouseListener);
  }

  public void showPanel(JPanel panel) {
    displayPanelContainer.removeAll();
    WebsiteAccount websiteAccount = this.scrollingDisplay.getWebsiteAccount(panel);
    this.currentlyDisplayedWebsite = websiteAccount;

    JPanel header = new JPanel(new BorderLayout());
    header.setBackground(Constants.MIDDLE_GROUND);

    BufferedImage logo = Backend.getUrlLogo(websiteAccount.getUrl());
    ImageIcon icon = ResourceLoader.makeLogo(logo);
    JLabel logoLabel = new JLabel(icon);

    JLabel urlLabel = new JLabel(websiteAccount.getUrl());
    urlLabel.setHorizontalAlignment(JLabel.CENTER);
    urlLabel.setFont(Constants.TEXT_FONT.deriveFont(Font.BOLD, 32));
    urlLabel.setForeground(Color.WHITE);

    header.add(logoLabel, BorderLayout.WEST);
    header.add(urlLabel);



    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Constants.MIDDLE_GROUND);

    JLabel username = new JLabel("Username");
    username.setFont(Constants.TEXT_FONT.deriveFont(Font.PLAIN, 14));
    username.setForeground(Color.lightGray);
    JLabel usernameLabel = new JLabel(websiteAccount.getUsername());
    usernameLabel.setFont(Constants.TEXT_FONT);
    usernameLabel.setForeground(Color.WHITE);

    JLabel password = new JLabel("Password");
    password.setFont(Constants.TEXT_FONT.deriveFont(Font.PLAIN, 14));
    password.setForeground(Color.lightGray);
    JLabel passwordLabel = new JLabel(websiteAccount.getPassword());
    passwordLabel.setFont(Constants.TEXT_FONT);
    passwordLabel.setForeground(Color.WHITE);

    IconButton editButton = new IconButton(Constants.ICONS.get("edit"));
    editButton.setHoverIcon(Constants.ICONS.get("edit_hover"));

    deleteButton = new IconButton(Constants.ICONS.get("delete"));
    deleteButton.setHoverIcon(Constants.ICONS.get("delete_hover"));
    MainPageController.addDeleteButton(this);



    infoPanel.add(Box.createVerticalStrut(25));
    infoPanel.add(username);
    infoPanel.add(Box.createVerticalStrut(5));
    infoPanel.add(usernameLabel);
    infoPanel.add(Box.createVerticalStrut(15));
    infoPanel.add(password);
    infoPanel.add(Box.createVerticalStrut(5));
    infoPanel.add(passwordLabel);
    infoPanel.add(Box.createVerticalStrut(15));
    infoPanel.add(editButton);
    infoPanel.add(deleteButton);


    JPanel websiteAccountPanel = new JPanel(new BorderLayout());
    websiteAccountPanel.setBackground(Constants.MIDDLE_GROUND);

    websiteAccountPanel.add(header, BorderLayout.NORTH);
    websiteAccountPanel.add(infoPanel, BorderLayout.CENTER);


    websiteAccountPanel.setPreferredSize(new Dimension((int) displayPanelContainer.getPreferredSize().getWidth() - 40, displayPanelContainer.getHeight()));
    displayPanelContainer.add(websiteAccountPanel, BorderLayout.CENTER);
    revalidate();
  }
  public void addPassword(ActionListener actionListener) {
    this.addButton.addActionListener(actionListener);
  }

  public void deletePassword(ActionListener actionListener) {
    if (this.deleteButton == null) {
      return;
    }
    this.deleteButton.addActionListener(actionListener);
  }

  public String getSearchText() {
    return this.searchBar.getText();
  }


  public void showUserWebsiteAccounts(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    scrollingDisplay.update(userWebsiteAccounts);
    MainPageController.updatePanelClickListener(this);
    revalidate();
  }

  public ArrayList<WebsiteAccount> getCurrentDisplayedWebsites() {
    return this.currentDisplayedWebsites;
  }

  public WebsiteAccount getCurrentlyDisplayedWebsite() {
    return this.currentlyDisplayedWebsite;
  }

  public void setCurrentDisplayedWebsites(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    this.currentDisplayedWebsites = userWebsiteAccounts;
  }
}
