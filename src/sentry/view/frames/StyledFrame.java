package sentry.view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StyledFrame extends JFrame {
  private Point initialClick;
  public StyledFrame() {
    super();
    styleFrame();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        requestFocusInWindow();
      }
    });
  }
  private void styleFrame() {
    // Remove default frame to create custom one
    setUndecorated(true);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
        getComponentAt(initialClick);
      }
    });

    // Add a mouse motion listener to the title bar
    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int thisX = getLocation().x;
        int thisY = getLocation().y;
        int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        setLocation(X, Y);
      }
    });
  }
}
