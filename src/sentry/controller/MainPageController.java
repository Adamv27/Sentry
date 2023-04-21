package sentry.controller;


import sentry.model.WebsiteAccount;
import sentry.view.panels.MainPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainPageController {

  public MainPageController(MainPanel mainPanel) {
    mainPanel.search(e -> {
      System.out.println(mainPanel.getSearchText());
    });
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
  }
}
