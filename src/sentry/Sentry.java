package sentry;

import sentry.UI.Login;
import sentry.utils.Constants;
import sentry.utils.ResourceLoader;
import sentry.model.SQLite;
import sentry.view.SentryFrame;

import javax.swing.*;
import java.util.HashMap;

public class Sentry {
  public static void main(String[] args) {
    ResourceLoader.load_resources();
    SQLite.setup();
    Constants.setup();
    SwingUtilities.invokeLater(SentryFrame::new);
    }
  
}