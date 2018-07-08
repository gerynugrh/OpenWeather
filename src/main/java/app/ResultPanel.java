package app;

import app.search.SearchPanel;
import app.weather.WeatherPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

class ResultPanel extends JPanel {

  private WeatherPanel weatherPanel;

  ResultPanel() {
    super();

    BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
    setLayout(boxLayout);
    setBackground(Color.WHITE);
    weatherPanel = new WeatherPanel();
    SearchPanel searchPanel = new SearchPanel(city -> {
      weatherPanel.setWeatherLocation(city);
      weatherPanel.updateWeather();
    });

    add("weather_details", weatherPanel);
    add("search", searchPanel);
  }

}
