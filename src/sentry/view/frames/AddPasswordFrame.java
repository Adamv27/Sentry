package sentry.view.frames;


import sentry.controller.AddPasswordController;

import sentry.view.panels.AddPasswordPanel;
import sentry.view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddPasswordFrame extends StyledFrame {

  private final int WIDTH = 500;
  private final int HEIGHT = 450;

  private final AddPasswordPanel panel;
  public AddPasswordFrame(MainPanel mainPanel) {
    super();
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    panel = new AddPasswordPanel();
    new AddPasswordController(panel, mainPanel);

    panel.close(e -> dispose());

    add(panel, BorderLayout.CENTER);

    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void addPassword(ActionListener actionListener) {
    panel.addPassword(actionListener);
  }
}
