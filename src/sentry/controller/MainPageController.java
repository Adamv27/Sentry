package sentry.controller;

import sentry.model.Backend;
import sentry.model.WebsiteAccount;
import sentry.view.frames.AddPasswordFrame;
import sentry.view.panels.MainPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainPageController {
  public MainPageController(MainPanel mainPanel) {

    mainPanel.search(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        StringBuilder input = new StringBuilder();

        // On a backspace we want the current input minus the last character
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
          String text = mainPanel.getSearchText().strip();
          if (text.length() > 0) {
            input.append(text, 0, text.length() - 1);
          }
          // On a character / digit entry get the input + the new entry
        } else if (Character.isLetterOrDigit(e.getKeyChar())) {
          input.append(mainPanel.getSearchText().strip()).append(e.getKeyChar());
        }

        ArrayList<WebsiteAccount> currentWebsites = mainPanel.getCurrentDisplayedWebsites();
        if (currentWebsites == null) {
          return;
          // If input has been fully deleted show all websites
        } else if (input.length() == 0) {
          mainPanel.showUserWebsiteAccounts(currentWebsites);
          return;
        }
        String finalInput = input.toString().toLowerCase();

        // Filter currently displayed websites to only show ones where the url
        // contains the search box input
        ArrayList<WebsiteAccount> filteredWebsites = currentWebsites.stream()
                .filter(websiteAccount -> websiteAccount.getUrl().toLowerCase().contains(finalInput))
                .collect(Collectors.toCollection(ArrayList::new));

        mainPanel.showUserWebsiteAccounts(filteredWebsites);
      }
    });

    mainPanel.addPassword(e -> {
      AddPasswordFrame addPasswordFrame = new AddPasswordFrame(mainPanel);
      addPasswordFrame.addPassword(click -> addPasswordFrame.dispose());
    });
  }

  public static void addDeleteButton(MainPanel mainPanel) {
    mainPanel.deletePassword(e -> {
      WebsiteAccount account = mainPanel.getCurrentlyDisplayedWebsite();
      if (Backend.deletePassword(account)) {
        ArrayList<WebsiteAccount> currentWebsites = mainPanel.getCurrentDisplayedWebsites();
        currentWebsites.remove(account);
        mainPanel.showUserWebsiteAccounts(currentWebsites);
        mainPanel.showPanel(null);
      }
    });
  }

  public static void addEditButton(MainPanel mainPanel) {
  }

  public static void updatePanelClickListener(MainPanel mainPanel) {
    mainPanel.showData(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof JPanel) {
          mainPanel.showPanel((JPanel) e.getComponent());
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });
  }
}
