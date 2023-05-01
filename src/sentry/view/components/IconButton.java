package sentry.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class IconButton extends JButton {

  private final ImageIcon icon;
  private ImageIcon hoverIcon;
  public IconButton(ImageIcon imageIcon) {
    super(imageIcon);
    this.icon = imageIcon;
    this.hoverIcon = imageIcon;
    setBorderPainted(false);
    setFocusPainted(false);
    setContentAreaFilled(false);

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        setIcon(hoverIcon);
      }
      public void mouseExited(MouseEvent e) {
        setIcon(icon);
      }
    });
  }

  public void setHoverIcon(ImageIcon icon) {
    this.hoverIcon = icon;
  }
}
