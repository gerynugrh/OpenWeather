import java.awt.FlowLayout;
import javax.swing.JPanel;

public class WeatherDetailsPanel extends JPanel {

  private JPanel context;

  public WeatherDetailsPanel(JPanel context) {
    super(new FlowLayout());
    this.context = context;
  }

}
