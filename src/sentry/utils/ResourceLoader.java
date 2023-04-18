package sentry.utils;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class ResourceLoader {
  private static HashMap<String, Font> FONTS = new HashMap<>();

  public static Font getFont(String fontName) {
    return FONTS.get(fontName);
  }

  public static Set<String> getFontNames() {
    return FONTS.keySet();
  }

  public static void load_resources() {
    loadFonts();
  }

  private static void loadFonts() {
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      File fontFile = new File("src/sentry/resources/cuyabra.ttf");

      Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
      font = font.deriveFont(Font.PLAIN, 25);
      FONTS.put(font.getFontName(), font);
      ge.registerFont(font);

      fontFile = new File("src/sentry/resources/Bord.ttf");
      font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
      font = font.deriveFont(Font.PLAIN, 45);
      FONTS.put(font.getFontName(), font);
      ge.registerFont(font);
    } catch (Exception e) {
      System.out.println("Could not load text font");
      e.printStackTrace();
    }
  }



}
