package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {

  private MainFrame() throws HeadlessException {
    super();
    setSize(new Dimension(1000, 600));
    setPreferredSize(new Dimension(1000, 600));
    // Define the container for all components inside this window
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    ResultPanel resultPanel = new ResultPanel();
    getContentPane().add(resultPanel);
    getContentPane().setBackground(new Color(255, 255, 255));
    pack();
    setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
    SwingUtilities.invokeLater(MainFrame::new);
  }

}
