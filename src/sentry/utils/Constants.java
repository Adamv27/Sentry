package sentry.utils;

import java.awt.*;

public class Constants {
  public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  // dark grey: 0x191A19
  public static final Color PANEL_BACKGROUND = new Color(0x101010);
  public static final Color LIGHT_GREY = new Color(0x171717);
  public static final Color LIGHT_GREEN = new Color(0x4E9F3D);


  public static final Font TEXT_FONT = ResourceLoader.getFont("cuyabra Regular");
  public static final Font TITLE_FONT = ResourceLoader.getFont("Bord Demo");

  private static void registerFonts() {
    System.out.println(ResourceLoader.getFontNames());

    for (String fontName : ResourceLoader.getFontNames()) {
      ge.registerFont(ResourceLoader.getFont(fontName));
    }
  }
}
