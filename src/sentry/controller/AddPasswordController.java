package sentry.controller;

import sentry.model.Backend;
import sentry.model.WebsiteAccount;
import sentry.view.panels.AddPasswordPanel;
import sentry.view.panels.MainPanel;

import java.util.ArrayList;

public class AddPasswordController {

  public AddPasswordController(AddPasswordPanel addPasswordPanel, MainPanel mainPanel) {
    addPasswordPanel.addPassword(e -> {
      String url = addPasswordPanel.getUrl();
      String username = addPasswordPanel.getUsername();
      String password = addPasswordPanel.getPassword();

      // Move to backend
      if (url.equals("") || username.equals("") || password.equals("")) {
        return;
      }

      WebsiteAccount websiteAccount = new WebsiteAccount(url, username, password);

      if (Backend.addNewPassword(websiteAccount)) {
        ArrayList<WebsiteAccount> currentlyDisplayed = mainPanel.getCurrentDisplayedWebsites();
        currentlyDisplayed.add(websiteAccount);
        mainPanel.showUserWebsiteAccounts(currentlyDisplayed);
      }
    });
  }
}
