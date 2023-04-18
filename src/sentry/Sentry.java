package sentry;

import sentry.UI.Login;
import sentry.utils.ResourceLoader;
import sentry.model.SQLite;

import java.util.HashMap;

public class Sentry {
  public static void main(String[] args) {
    ResourceLoader.load_resources();
    SQLite.setup();
    new Login();
  }
  
}