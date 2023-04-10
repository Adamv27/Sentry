package sentry.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainContentPaneSwitching {

  private static class ChangeContentPaneListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JPanel newFrameContents = new JPanel(); //Uses FlowLayout by default.
      newFrameContents.add(new JLabel("You have successfully changed the content pane of the frame!", JLabel.CENTER));

            /*We assume that the source is a JButton and that the Window is of type JFrame, hence
            the following utility method call is possible without letting any errors appear:*/
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());

      frame.setContentPane(newFrameContents); //Change the content pane of the frame.
      frame.revalidate(); //Notify the frame that the component hierarchy has changed.
      frame.pack(); //Resize the frame as necessary in order to fit as many contents as possible in the screen.
      frame.setLocationRelativeTo(null); //Place the frame in the center of the screen. As you can tell, this needs its size to calculate the location, so we made sure in the previous line of code that it is set.
      frame.repaint(); //Repaint frame with all its contents.
    }
  }

  private static class MainRunnable implements Runnable {
    @Override
    public void run() {
      JButton changeContentPaneButton = new JButton("Click to go to the next content pane!");
      changeContentPaneButton.addActionListener(new ChangeContentPaneListener());

      JPanel frameContents = new JPanel(); //Uses FlowLayout by default.
      frameContents.add(changeContentPaneButton);

      JFrame frame = new JFrame("My application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tells the frame that when the user closes it, it must terminate the application.
      frame.setContentPane(frameContents); //Add contents to the frame.
      frame.pack(); //Resize the frame as necessary in order to fit as many contents as possible in the screen.
      frame.setLocationRelativeTo(null); //Place the frame in the center of the screen. As you can tell, this needs its size to calculate the location, so we made sure in the previous line of code that it is set.
      frame.setVisible(true);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new MainRunnable()); //Swing code must always be used in the Event Dispatch Thread.
  }
}