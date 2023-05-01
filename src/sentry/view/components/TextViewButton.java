package sentry.view.components;

import sentry.utils.Constants;

import java.awt.*;

public class TextViewButton {

  private final Image image;
  private final Image clickImage;
  private boolean active;

  public TextViewButton() {
    this.image = Constants.ICONS.get("view_unselected").getImage();
    this.clickImage = Constants.ICONS.get("view_selected").getImage();
    this.active = false;
  }

  public void draw(Graphics g, int x, int y) {
    Image drawImage = this.active ? clickImage : image;
    g.drawImage(drawImage, x, y, null);
  }

  public boolean contains(Point location, Point click) {
    int buttonWidth = location.x + image.getWidth(null);
    int buttonHeight = location.x + image.getHeight(null);
    return (click.x >= location.x && click.x <= buttonWidth) && (click.y >= location.y && click.y <= buttonHeight);
  }

  public void toggle() {
    this.active = !this.active;
  }

  public char getEchoChar() {
    if (this.active) {
      return (char) 0;
    }
    return '•';
  }
}
