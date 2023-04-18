package sentry.view.components;

import sentry.UI.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJButton extends JButton {
  private Shape shape;

  public RoundJButton(String text) {
    super(text);
    this.setBackground(Constants.LIGHT_GREY);
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    setOpaque(false); // As suggested by @AVD in comment.
  }

  protected void paintComponent(Graphics g) {
    g.setColor(Constants.LIGHT_GREEN);
    g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    super.paintComponent(g);
  }

  protected void paintBorder(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    GradientPaint gp = new GradientPaint(0, 0, Constants.LIGHT_GREEN, getWidth() - 1, getHeight() - 1, Constants.DARK_GREY);
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