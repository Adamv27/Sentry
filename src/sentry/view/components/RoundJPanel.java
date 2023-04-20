package sentry.view.components;

import sentry.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundJPanel extends JPanel {

  private final int cornerRadius;

  public RoundJPanel(int cornerRadius) {
    this.cornerRadius = cornerRadius;
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Cast to Graphics2D for drawing operations
    Graphics2D g2d = (Graphics2D) g.create();

    g2d.setColor(getBackground());
    // Set anti-aliasing for smoother edges
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Draw a rounded rectangle with the specified corner radius
    g2d.fillRoundRect(0,0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
    // Dispose the graphics object
    g2d.dispose();
  }

  public void setMargins(int margins) {
    setBorder(new EmptyBorder(margins, margins, margins, margins));
  }
}
