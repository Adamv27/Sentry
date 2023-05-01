package sentry.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ResourceLoader {
  private static final String iconPath = "src/sentry/resources/icons/";
  private static final String fontPath = "src/sentry/resources/fonts/";

  private static final HashMap<String, Font> FONTS = new HashMap<>();

  /**
   * Retrieve requested font given name
   * @param fontName name of font
   * @return Font
   */
  public static Font getFont(String fontName) {
    return FONTS.get(fontName);
  }

  public static void load_resources() {
    loadFonts();
  }

  /**
   * Registers fonts to the graphics environment and adds
   * them to a map
   */
  private static void loadFonts() {
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      File fontFile = new File(fontPath + "cuyabra.ttf");

      Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
      font = font.deriveFont(Font.PLAIN, 25);
      FONTS.put(font.getFontName(), font);
      ge.registerFont(font);

      fontFile = new File(fontPath + "Bord.ttf");
      font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
      font = font.deriveFont(Font.PLAIN, 45);
      FONTS.put(font.getFontName(), font);
      ge.registerFont(font);
    } catch (Exception e) {
      System.out.println("Could not load text font");
      e.printStackTrace();
    }
  }

  /**
   * Creates a single ImageIcon given a file
   * @param iconFile path to icon file
   * @return ImageIcon of loaded + scaled file
   */
  private static ImageIcon getIcon(String iconFile) {
    try {
      BufferedImage originalImage = ImageIO.read(new File(iconPath + iconFile));
      int width = 25;
      int height = 25;
      Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return new ImageIcon(scaledImage);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Iterates through entire resources/icons/ folder adds ImageIcon to map with its
   * filename as the key.
   * @return map of all ImageIcons
   */
  public static HashMap<String, ImageIcon> getIcons() {
    try {
      HashMap<String, ImageIcon> iconMap = new HashMap<>();
      File path = new File(iconPath);
      for (File file : Objects.requireNonNull(path.listFiles())) {
        if (!file.isFile()) {
          continue;
        }
        ImageIcon icon = getIcon(file.getName());
        // Removes extension to use as key for map
        String fileName = file.getName().replaceFirst("[.][^.]+$", "");
        iconMap.put(fileName, icon);
      }
      return iconMap;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates the rounded logo picture.
   *
   * @param image original square image of logo
   * @return circular image of logo
   */
  public static ImageIcon makeLogo(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = output.createGraphics();

    // This is what we want, but it only does hard-clipping, i.e. aliasing
    // g2.setClip(new RoundRectangle2D ...)

    // so instead fake soft-clipping by first drawing the desired clip shape
    // in fully opaque white with antialiasing enabled...
    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, w, h));

    // ... then compositing the image on top,
    // using the white shape from above as alpha source
    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);

    g2.dispose();

    Image scaledImage = output.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }
}
