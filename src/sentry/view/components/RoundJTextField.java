package sentry.view.components;

import sentry.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JPasswordField {
  private Shape shape;

  private final String placeholder;

  private boolean hideText;

  private final TextViewButton viewButton;

  public RoundJTextField(int columns, String placeholder) {
    super(columns);
    this.placeholder = placeholder;
    this.hideText = false;
    viewButton = new TextViewButton();

    setEchoChar((char) 0);
    setBackground(Constants.LIGHT_GREY);
    setForeground(Constants.TEXT_UNFOCUSED);
    setHorizontalAlignment(JTextField.CENTER);

    setText(placeholder);
    setOpaque(false); // As suggested by @AVD in comment.
    createFocusListener();
    createMouseListener();
  }

  protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);

    if (this.hideText) {
      viewButton.draw(g, getWidth() - 50, getHeight() / 4);
    }
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

  @Override
  public String getText() {
    return new String(getPassword());
  }

  public boolean isEmpty() {
    String str = new String(getPassword());
    return str.equals(placeholder) || str.equals("");
  }

  public String getPlaceholder() {
    return placeholder;
  }

  private void createFocusListener() {
    addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        // Lighten text on focus
        setForeground(Constants.TEXT_FOCUSED);
        // If placeholder is still there, remove it
        if (getText().equals(placeholder)) {
          setText("");
          if (hideText) {
            // If it's a password field, replace text with echo character
            setEchoChar('•');
          }
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        // If the field is empty and focused is lost, re-add the placeholder
        if (getText().equals("")) {
          setForeground(Constants.TEXT_UNFOCUSED);
          setText(placeholder);
          setEchoChar((char) 0);
        }
      }
    });
  }

  private void createMouseListener() {
    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point location = new Point(getWidth() - 50, getHeight() / 4);
        if (viewButton.wasClicked(location, e.getPoint())) {
          viewButton.toggle();
          setEchoChar(viewButton.getEchoChar());
          repaint();
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });
  }

  public void hideText(boolean value) {
    this.hideText = value;
  }
}

