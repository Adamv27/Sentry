package sentry.utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Constants {
  public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  // dark grey: 0x191A19
  public static final Color PANEL_BACKGROUND = new Color(0x121212);
  public static final Color MIDDLE_GROUND = new Color(0x202020);
  public static final Color LIGHT_GREY = new Color(0x333333);
  public static final Color LIGHT_GREEN = new Color(0x4E9F3D);

  public static final Color TEXT_FOCUSED = Color.white;
  public static final Color TEXT_UNFOCUSED = new Color(0x767676);


  public static final Font TEXT_FONT = ResourceLoader.getFont("cuyabra Regular");
  public static final Font TITLE_FONT = ResourceLoader.getFont("Bord Demo");


  public static int MAIN_FRAME_WIDTH;
  public static int MAIN_FRAME_HEIGHT;

  public static HashMap<String, ImageIcon> ICONS;

  public static void setup() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    System.out.println("WIDTH: " + screenWidth + " HEIGHT: " + screenHeight);
    MAIN_FRAME_WIDTH = 800;
    MAIN_FRAME_HEIGHT = 540;

    ICONS = ResourceLoader.getIcons();
    if (ICONS == null) {
      System.exit(1);
    }
  }

  private static void registerFonts() {
    System.out.println(ResourceLoader.getFontNames());

    for (String fontName : ResourceLoader.getFontNames()) {
      ge.registerFont(ResourceLoader.getFont(fontName));
    }
  }
}
