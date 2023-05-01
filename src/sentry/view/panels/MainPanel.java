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

  /**
   * Adds key listener to search bar
   * @param keyListener key listener to listen for key presses
   */
  public void search(KeyListener keyListener) {
    this.searchBar.addKeyListener(keyListener);
  }


  /**
   * Add mouse listener to scrolling display to check for clicks requesting
   * to show the information for a website.
   * @param mouseListener mouse listener to listen for clicking
   */
  public void showData(MouseListener mouseListener) {
    this.scrollingDisplay.showData(mouseListener);
  }

  /**
   * Action listener added to the add button that opens
   * up new GUI to add new password
   * @param actionListener action listener to listen for button click
   */
  public void addPassword(ActionListener actionListener) {
    this.addButton.addActionListener(actionListener);
  }

  /**
   * Action listener added to the delete button to delete
   * the currently displayed password
   * @param actionListener action listener to listen for button click
   */
  public void deletePassword(ActionListener actionListener) {
    if (this.deleteButton == null) {
      return;
    }
    this.deleteButton.addActionListener(actionListener);
  }

  /**
   * Get the current text in the search bar
   * @return search bars text
   */
  public String getSearchText() {
    return this.searchBar.getText();
  }

  /**
   * Updates scrolling display and refreshes the main panel
   * @param userWebsiteAccounts list of website accounts to display
   */
  public void showUserWebsiteAccounts(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    scrollingDisplay.update(userWebsiteAccounts);
    MainPageController.updatePanelClickListener(this);
    revalidate();
  }

  /**
   * Retrieve list of all displayed websites.
   * @return all currently displayed websites
   */
  public ArrayList<WebsiteAccount> getCurrentDisplayedWebsites() {
    return this.currentDisplayedWebsites;
  }

  /**
   * Retrieve the singular website displayed on the right hand side.
   * @return website being displayed in the display panel
   */
  public WebsiteAccount getCurrentlyDisplayedWebsite() {
    return this.currentlyDisplayedWebsite;
  }

  /**
   * Update stored list of websites that are being displayed
   * @param userWebsiteAccounts list of websites
   */
  public void setCurrentDisplayedWebsites(ArrayList<WebsiteAccount> userWebsiteAccounts) {
    this.currentDisplayedWebsites = userWebsiteAccounts;
  }

  /**
   * The display panel is the rounded panel on the right hand side of the main screen.
   * Its purpose is to hold the JPanel containing the information of the website currently
   * being displayed.
   */
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

  /**
   * The data panel is the rounded panel on the left hand side of the main screen.
   * It holds the search bar, add button, and scrolling display of available websites.
   */
  private void createDataPanel() {

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

  /**
   * Creates JPanel holding search bar and add button.
   *
   * @param container parent container
   * @return JPanel containing search bar and add button
   */
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

  /**
   * Creates a new scrolling display.
   *
   * @param container parent container
   * @return finished scrolling display
   */
  private JPanel createPasswordDisplay(JPanel container) {
    scrollingDisplay = new ScrollingDisplay(container);
    return scrollingDisplay;
  }

  /**
   * If a website is clicked on the right side, all of its information is displayed
   * on the left
   * @param panel panel that was clicked
   */
  public void showPanel(JPanel panel) {
    displayPanelContainer.removeAll();
    repaint();
    if (panel == null) {
      return;
    }

    // Get the corresponding website account of the panel that was clicked
    WebsiteAccount websiteAccount = this.scrollingDisplay.getWebsiteAccount(panel);
    this.currentlyDisplayedWebsite = websiteAccount;

    // The header panel holds the websites logo and title
    JPanel header = new JPanel(new BorderLayout());
    header.setBackground(Constants.MIDDLE_GROUND);

    BufferedImage logo = Backend.getUrlLogo(websiteAccount.getUrl());
    if (logo != null) {
      ImageIcon icon = ResourceLoader.makeLogo(logo);
      JLabel logoLabel = new JLabel(icon);
      header.add(logoLabel, BorderLayout.WEST);
    }

    JLabel siteLabel = new JLabel(websiteAccount.getWebsiteName());
    siteLabel.setHorizontalAlignment(JLabel.CENTER);
    siteLabel.setFont(Constants.TEXT_FONT.deriveFont(Font.BOLD, 32));
    siteLabel.setForeground(Color.WHITE);
    header.add(siteLabel, BorderLayout.CENTER);

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Constants.MIDDLE_GROUND);

    JLabel url = new JLabel("URL");
    url.setFont(Constants.TEXT_FONT.deriveFont(Font.PLAIN, 12));
    url.setForeground(Color.lightGray);
    JLabel urlLabel = new JLabel(websiteAccount.getUrl());
    urlLabel.setFont(Constants.TEXT_FONT);
    urlLabel.setForeground(Color.WHITE);

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
    infoPanel.add(url);
    infoPanel.add(Box.createVerticalStrut(5));
    infoPanel.add(urlLabel);
    infoPanel.add(Box.createVerticalStrut(15));
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
}
