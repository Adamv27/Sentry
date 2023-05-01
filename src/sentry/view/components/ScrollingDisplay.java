package sentry.view.components;

import sentry.model.WebsiteAccount;
import sentry.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class ScrollingDisplay extends JPanel {
  private ArrayList<WebsiteAccount> websites;
  private final JScrollPane scrollPane;

  private final JPanel contentPane;

  private ArrayList<JPanel> panels;

  public ScrollingDisplay(JPanel container) {
    this(new ArrayList<>(), container);
  }
  public ScrollingDisplay(ArrayList<WebsiteAccount> websites, JPanel container) {
    this.websites = websites;
    this.panels = new ArrayList<>();
    setBackground(Constants.MIDDLE_GROUND);

    // Create scroll pane which holds panel containing websites
    scrollPane = new JScrollPane(this);
    setLayout(new BorderLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    scrollPane.setBackground(Constants.MIDDLE_GROUND);


    // Content pane holds every website
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBackground(Constants.MIDDLE_GROUND);
    contentPane.add(Box.createVerticalGlue());
    addWebsites();
    contentPane.add(Box.createVerticalGlue());


    addScrollBarStyling();

    setPreferredSize(new Dimension(container.getWidth(), container.getHeight()));
  }

  /*
    Rebuilds scrolling display with new websites.
   */
  public void update(ArrayList<WebsiteAccount> websites) {
    this.websites = websites;
    this.panels = new ArrayList<>();
    contentPane.removeAll();
    addWebsites();
  }

  public void showData(MouseListener mouseListener) {
    panels.forEach(panel -> panel.addMouseListener(mouseListener));
  }

  public WebsiteAccount getWebsiteAccount(JPanel panel) {
    if (panels.contains(panel)) {
      int index = panels.indexOf(panel);
      return websites.get(index);
    }
    return null;
  }

  /*
    Adds a panel containing the website URL for each
    website in the display list.
   */
  private void addWebsites() {
    for (WebsiteAccount website : websites) {
      RoundJPanel websitePanel = createWebsitePanel(website);
      panels.add(websitePanel);
      contentPane.add(websitePanel);
      contentPane.add(Box.createVerticalStrut(5));
    }
    scrollPane.setViewportView(contentPane);
    add(scrollPane);
  }


  /**
   *
   * @param website website to be displayed in panel
   * @return completed panel with styling and correct information
   */
  private RoundJPanel createWebsitePanel(WebsiteAccount website) {
    RoundJPanel websitePanel = new RoundJPanel(18);
    websitePanel.setLayout(new BoxLayout(websitePanel, BoxLayout.X_AXIS));
    websitePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    websitePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    websitePanel.setBackground(Constants.LIGHT_GREY);
    JLabel title = new JLabel(website.getWebsiteName());
    title.setFont(Constants.TEXT_FONT.deriveFont(Font.PLAIN, 20));
    title.setForeground(Color.WHITE);

    websitePanel.setPreferredSize(new Dimension(350, 100));
    websitePanel.setMaximumSize(new Dimension(350, 100));
    websitePanel.add(Box.createHorizontalGlue());
    websitePanel.add(title);
    websitePanel.add(Box.createHorizontalGlue());
    websitePanel.add(Box.createVerticalGlue());
    return websitePanel;
  }

  /**
   * This method changes the scroll bars styling and makes it look cleaner.
   * It removes The border, the scroll buttons, and it sets the bar to
   * a solid black rectangle.
   */
  private void addScrollBarStyling() {
    scrollPane.getVerticalScrollBar().setBackground(null);
    scrollPane.getVerticalScrollBar().setBackground(Constants.LIGHT_GREY);

    scrollPane.setPreferredSize(new Dimension(450, 350));
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      private final Dimension d = new Dimension();
      @Override
      protected void configureScrollBarColors() {
        this.thumbColor = Color.BLACK;
        this.decrButton = null;
        this.incrButton = null;
      }
      @Override
      protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
          @Override public Dimension getPreferredSize() {
            return d;
          }
        };
      }
      @Override
      protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
          @Override
          public Dimension getPreferredSize() {
            return d;
          }
        };
      }
    });
  }
}
