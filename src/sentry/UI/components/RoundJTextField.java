package sentry.UI.components;

import sentry.UI.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JTextField {
  private Shape shape;

  public RoundJTextField(int size) {
    super(size);
    this.setBackground(Constants.LIGHT_GREY);
    this.setForeground(Color.WHITE);
    this.setHorizontalAlignment(JTextField.CENTER);
    setOpaque(false); // As suggested by @AVD in comment.
  }
  protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    super.paintComponent(g);
  }
  protected void paintBorder(Graphics g) {
    g.setColor(new Color(0x252525));
    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
  }
  public boolean contains(int x, int y) {
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    return shape.contains(x, y);
  }
}
