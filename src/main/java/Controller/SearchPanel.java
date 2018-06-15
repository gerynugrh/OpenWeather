package Controller;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SearchPanel extends JPanel {

  private JPanel context;

  public SearchPanel(JPanel context) {
    super(new FlowLayout());
    this.context = context;

    add(new JTextArea("Search Panel"));
  }
}
