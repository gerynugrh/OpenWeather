package Controller;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

  JPanel mainPanel;
  private JTextField searchField = new JTextField();
  private ResultPanel resultPanel = new ResultPanel();

  private MainFrame() throws HeadlessException {
    super();
    // Define the container for all components inside this window
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    // Add the component into the container defined above
    searchField.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("Text Field Clicked");
        resultPanel.getLayoutManager().show(resultPanel, "search");
      }
    });
    getContentPane().add(searchField);
    getContentPane().add(resultPanel);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MainFrame();
      }
    });
  }

}
