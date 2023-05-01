package sentry;


import sentry.utils.Constants;
import sentry.utils.ResourceLoader;
import sentry.model.SQLite;
import sentry.view.frames.SentryFrame;

import javax.swing.*;

public class Sentry {
  public static void main(String[] args) {

    /*
      TO DO!!!!!!!!!!!!
        MAJOR:
          - Implement sign up page

          - Add multiple accounts per site

          - Add edit functionality

          - Add error messages

          - ADD RESIZING

        MINOR:
          - Add log out button

          - Add password encryption

          - On delete - clear right side display

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