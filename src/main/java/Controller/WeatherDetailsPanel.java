package Controller;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WeatherDetailsPanel extends JPanel {

  private JPanel context;

  public WeatherDetailsPanel(JPanel context) {
    super(new FlowLayout());
    this.context = context;

    add(new JTextArea("WeatherDetails Panel"));
  }

}
