package sentry.utils;

import java.awt.*;

public class Constants {
  public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  // dark grey: 0x191A19
  public static final Color PANEL_BACKGROUND = new Color(0x121212);
  public static final Color MIDDLE_GROUND = new Color(0x202020);
  public static final Color LIGHT_GREY = new Color(0x333333);
  public static final Color LIGHT_GREEN = new Color(0x4E9F3D);


  public static final Font TEXT_FONT = ResourceLoader.getFont("cuyabra Regular");
  public static final Font TITLE_FONT = ResourceLoader.getFont("Bord Demo");


  public static int MAIN_FRAME_WIDTH;
  public static int MAIN_FRAME_HEIGHT;

  public static void setup() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    MAIN_FRAME_WIDTH = screenWidth / 2;
    MAIN_FRAME_HEIGHT = (int) (screenHeight / 1.5);
  }

  private static void registerFonts() {
    System.out.println(ResourceLoader.getFontNames());

    for (String fontName : ResourceLoader.getFontNames()) {
      ge.registerFont(ResourceLoader.getFont(fontName));
    }
  }
}
