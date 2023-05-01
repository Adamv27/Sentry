package sentry.view.components;

import sentry.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class RoundJButton extends JButton {
  private Shape shape;

  public RoundJButton(String text) {
    super(text);
    this.setBackground(Constants.LIGHT_GREEN);
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    this.setContentAreaFilled(false);
    setOpaque(false); // As suggested by @AVD in comment.

    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {

      }

      @Override
      public void mousePressed(MouseEvent e) {
        setBackground(Constants.LIGHT_GREEN.darker());
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        setBackground(Constants.LIGHT_GREEN);
      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });
  }

  protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    super.paintComponent(g);
  }

  protected void paintBorder(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    GradientPaint gp = new GradientPaint(0, 0, Constants.LIGHT_GREEN, getWidth() - 1, getHeight() - 1, Constants.PANEL_BACKGROUND);
    g2d.setPaint(gp);
    g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
  }

  public boolean contains(int x, int y) {
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }
    return shape.contains(x, y);
  }
}