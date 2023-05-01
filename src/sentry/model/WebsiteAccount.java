package sentry.model;

import sentry.utils.Constants;
import sentry.view.components.CircularImageIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class WebsiteAccount {
  private String url;
  private String username;
  private String password;

  private CircularImageIcon icon;

  public WebsiteAccount(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;

//    try {
//      URL iconURL = new URL("https://logo.clearbit.com/" + url);
//      BufferedImage image = ImageIO.read(iconURL);
//      ImageIcon imageIcon = new ImageIcon(image);
//
//      icon = new CircularImageIcon(imageIcon, Constants.MIDDLE_GROUND);
//    } catch (Exception e) {
//      System.out.println("Getting icon from: " + "https://logo.clearbit.com/" + url);
//      e.printStackTrace();
//    }
  }

  public CircularImageIcon getIcon() {
    return this.icon;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String toString() {
    return "URL: " + this.url + " Username: " + this.username + " Password: " + this.password;
  }
}
