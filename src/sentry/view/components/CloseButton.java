package sentry.view.components;

import sentry.UI.Constants;
import sentry.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseButton extends JButton {
  public CloseButton(String text, JFrame frame) {
    super(text);
    this.setBackground(Constants.LIGHT_GREY);
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    //setOpaque(false);
    setFocusable(false);
    setForeground(Color.WHITE);
    //setContentAreaFilled(false);
    addActionListener(e -> frame.dispose());

    setBorder(null);
    setFont(ResourceLoader.getFont("cuyabra Regular"));

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
        setBackground(Constants.LIGHT_GREY);
      }
    });
  }



}
