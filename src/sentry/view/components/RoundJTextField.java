package sentry.view.components;

import sentry.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JPasswordField {
  private Shape shape;

  private final String placeholder;

  private boolean hideText;

  public RoundJTextField(int columns, String placeholder) {
    super(columns);
    this.placeholder = placeholder;
    this.hideText = false;

    setEchoChar((char) 0);
    setBackground(Constants.LIGHT_GREY);
    setForeground(Constants.TEXT_UNFOCUSED);
    setHorizontalAlignment(JTextField.CENTER);

    setText(placeholder);
    setOpaque(false); // As suggested by @AVD in comment.
    createFocusListener();
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

  @Override
  public String getText() {
    return new String(getPassword());
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

  public void hideText(boolean value) {
    this.hideText = value;
  }
}

