package sentry.view.panels;

import sentry.utils.Constants;
import sentry.view.components.CloseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/*
  The TitleBar class replaces the old bar which was
  removed when setUndecorated(true) was called in
  the frame class.
 */
public class TitleBar extends JPanel {
  JPanel buttonPanel;
  CloseButton closeButton;
  public TitleBar() {
    super(new BorderLayout());
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    closeButton = new CloseButton("x");
    buttonPanel.add(closeButton);

    add(buttonPanel, BorderLayout.NORTH);

    addStyle();
  }

  private void addStyle() {
    setBackground(Constants.LIGHT_GREEN);
    buttonPanel.setBackground(Constants.PANEL_BACKGROUND);
  }
}
