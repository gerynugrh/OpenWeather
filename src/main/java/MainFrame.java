import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

  private MainFrame() throws HeadlessException {
    super();
    getContentPane().add(new JTextField());
    getContentPane().add(new MainPanel());
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
