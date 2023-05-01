package sentry.utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Constants {
  // dark grey: 0x191A19
  public static final Color PANEL_BACKGROUND = new Color(0x121212);
  public static final Color MIDDLE_GROUND = new Color(0x202020);
  public static final Color LIGHT_GREY = new Color(0x333333);
  public static final Color LIGHT_GREEN = new Color(0x4E9F3D);

  public static final Color TEXT_FOCUSED = Color.white;
  public static final Color TEXT_UNFOCUSED = new Color(0x767676);


  public static final Font TEXT_FONT = ResourceLoader.getFont("cuyabra Regular");
  public static final Font TITLE_FONT = ResourceLoader.getFont("Bord Demo");


  public static int MAIN_FRAME_WIDTH = 960;
  public static int MAIN_FRAME_HEIGHT = 720;
  public static int LOGIN_FRAME_WIDTH = 600;
  public static int LOGIN_FRAME_HEIGHT = 450;

  public static HashMap<String, ImageIcon> ICONS;

  public static void setup() {
    ICONS = ResourceLoader.getIcons();
    if (ICONS == null) {
      System.exit(1);
    }
  }
}
