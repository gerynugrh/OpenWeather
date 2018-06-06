import java.awt.FlowLayout;
import javax.swing.JPanel;

public class SearchPanel extends JPanel {

  private JPanel context;

  public SearchPanel(JPanel context) {
    super(new FlowLayout());
    this.context = context;
  }
}
