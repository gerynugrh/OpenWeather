package Controller;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {

  private SearchPanel searchPanel;
  private WeatherDetailsPanel weatherPanel;
  private CardLayout cardLayout = new CardLayout();

  public ResultPanel() {
    super();

    setLayout(cardLayout);
    searchPanel = new SearchPanel(this);
    weatherPanel = new WeatherDetailsPanel(this);

    add("weather_details", weatherPanel);
    add("search", searchPanel);
  }

  public CardLayout getLayoutManager() {
    return cardLayout;
  }

  public void setWeatherLocation() {

  }

}
