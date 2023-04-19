package sentry.view.components;

import sentry.utils.Constants;
import sentry.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseButton extends JButton {
  public CloseButton(String text) {
    super(text);
    this.setBackground(Constants.PANEL_BACKGROUND);
    this.setFocusPainted(false);
    //setOpaque(false);
    setFocusable(false);
    setForeground(Color.WHITE);
    //setContentAreaFilled(false);
    addActionListener(e -> System.exit(0));
    setHorizontalAlignment(SwingConstants.CENTER);
    setPreferredSize(new Dimension(30, 30));

    setBorder(null);
    setFont(Constants.TEXT_FONT);

    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {

      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {
        setBackground(Color.RED);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        setBackground(Constants.PANEL_BACKGROUND);
      }
    });
  }



}
