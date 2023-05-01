package sentry.view.components;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CircularImageIcon extends JLabel {
  private final Color borderColor;

  private BufferedImage image;

  public CircularImageIcon(BufferedImage image, Color borderColor) {
    this.borderColor = borderColor;
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    setBackground(Color.blue);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    // Enable anti-aliasing for smoother edges
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Create a circle shape and clip the graphics context to it
    int diameter = Math.min(image.getWidth(), image.getHeight());
    Shape circle = new Ellipse2D.Double(0, 0, diameter, diameter);
    g2d.setClip(circle);

    // Draw the image in the clipped graphics context
    int x = (diameter - image.getWidth()) / 2;
    int y = (diameter - image.getHeight()) / 2;
    g2d.drawImage(image, x, y, null);

    g2d.dispose();
  }
}