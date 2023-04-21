package sentry;


import sentry.utils.Constants;
import sentry.utils.ResourceLoader;
import sentry.model.SQLite;
import sentry.view.SentryFrame;

import javax.swing.*;

public class Sentry {
  public static void main(String[] args) {

    /*
      TO DO!!!!!!!!!!!!
        MAJOR:
          - Implement sign up page
          - Implement add button
          - Implement scroll display functionality
              - Display current website and data on click
          - Add edit functionality

          - ADD RESIZING

        MINOR:
          - Add log out button
          - Fix one pixel white bar on right side

        CLEAN UP:
          - Change method names in main view
          - Remove commented out code
          - Remove unused code and imports
          - Split GUI code into more methods for readability
     */
    ResourceLoader.load_resources();
    SQLite.setup();
    Constants.setup();
    SwingUtilities.invokeLater(SentryFrame::new);
    }
  
}