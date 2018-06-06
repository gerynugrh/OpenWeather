import java.awt.CardLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private SearchPanel searchPanel;
  private WeatherDetailsPanel weatherPanel;

  public MainPanel() {
    super(new CardLayout());

    searchPanel = new SearchPanel(this);
    weatherPanel = new WeatherDetailsPanel(this);

    add("search", searchPanel);
    add("weather_details", weatherPanel);
  }

  public void setWeatherLocation() {

  }

}
