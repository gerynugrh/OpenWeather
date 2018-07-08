package app.weather;

import app.weather.current.CurrentWeatherDetail;
import app.weather.daily.DailyWeather;
import app.weather.daily.DailyWeatherDetail;
import app.weather.daily.DailyWeatherSummary;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import app.location.City;
import org.apache.commons.text.WordUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherPanel extends JPanel {

  private City weatherLocation;
  private JLabel placeholder = new JLabel();
  private JLabel temperatureLabel = new JLabel();
  private JLabel locationLabel = new JLabel();
  private JLabel weatherDescriptionLabel = new JLabel();
  private JLabel timeUpdatedLabel = new JLabel();
  private JList dailyWeatherJList = new JList();
  private JLabel windSpeedLabel = new JLabel();
  private JLabel humidityLabel = new JLabel();
  private JLabel visibilityLabel = new JLabel();
  private JLabel pressureLabel = new JLabel();

  public WeatherPanel() {
    super(new FlowLayout());
    setLayout(new GridBagLayout());
    setBackground(Color.WHITE);
    GridBagConstraints c = new GridBagConstraints();

    locationLabel.setToolTipText("Pin location");

    JPanel currentWeatherPanel = new JPanel();
    currentWeatherPanel.setLayout(new BoxLayout(currentWeatherPanel, BoxLayout.Y_AXIS));
    currentWeatherPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    currentWeatherPanel.setBackground(Color.WHITE);

    currentWeatherPanel.add(locationLabel);
    currentWeatherPanel.add(temperatureLabel);
    currentWeatherPanel.add(weatherDescriptionLabel);
    currentWeatherPanel.add(timeUpdatedLabel);

    JPanel windHumidityContainer = new JPanel();
    windHumidityContainer.setBackground(Color.WHITE);
    windHumidityContainer.setLayout(new BoxLayout(windHumidityContainer, BoxLayout.X_AXIS));
    windHumidityContainer.add(windSpeedLabel);
    windHumidityContainer.add(humidityLabel);

    currentWeatherPanel.add(windHumidityContainer);

    JPanel pressureVisibilityContainer = new JPanel();
    pressureVisibilityContainer.setBackground(Color.WHITE);
    pressureVisibilityContainer
        .setLayout(new BoxLayout(pressureVisibilityContainer, BoxLayout.X_AXIS));
    pressureVisibilityContainer.add(pressureLabel);
    pressureVisibilityContainer.add(visibilityLabel);

    currentWeatherPanel.add(pressureVisibilityContainer);

    JPanel currentWeatherPanelContainer = new JPanel();
    currentWeatherPanelContainer.setLayout(new BorderLayout());
    currentWeatherPanelContainer.add(currentWeatherPanel, BorderLayout.SOUTH);
    currentWeatherPanelContainer.setBackground(Color.WHITE);
    currentWeatherPanel.setBackground(Color.WHITE);

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.5;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(currentWeatherPanelContainer, c);

    c.weightx = 0.5;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(initDailyWeatherView(), c);

    temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    temperatureLabel.setFont(new Font(temperatureLabel.getFont().getName(), Font.PLAIN, 80));
    temperatureLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
    locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    locationLabel.setFont(new Font(locationLabel.getFont().getName(), Font.PLAIN, 22));
    locationLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
    weatherDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    weatherDescriptionLabel
        .setFont(new Font(weatherDescriptionLabel.getFont().getName(), Font.PLAIN, 22));
    weatherDescriptionLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
    timeUpdatedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    timeUpdatedLabel.setFont(new Font(timeUpdatedLabel.getFont().getName(), Font.PLAIN, 11));
    timeUpdatedLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
    windSpeedLabel.setBorder(new EmptyBorder(5, 10, 2, 10));
    windSpeedLabel.setBackground(Color.WHITE);
    humidityLabel.setBorder(new EmptyBorder(5, 10, 2, 10));
    humidityLabel.setBackground(Color.WHITE);
    pressureLabel.setBorder(new EmptyBorder(2, 10, 5, 10));
    pressureLabel.setBackground(Color.WHITE);
    visibilityLabel.setBorder(new EmptyBorder(2, 10, 5, 10));
    visibilityLabel.setBackground(Color.WHITE);
  }

  private JPanel initDailyWeatherView() {
    JPanel dailyWeatherPanel = new JPanel();
    dailyWeatherPanel.setLayout(new GridBagLayout());
    dailyWeatherPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
    dailyWeatherPanel.setBackground(Color.WHITE);

    DefaultListModel<DailyWeatherSummary> defaultListModel = new DefaultListModel<>();
    dailyWeatherJList.setCellRenderer(new WeatherElementRenderer());
    dailyWeatherJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    dailyWeatherJList.setModel(defaultListModel);
    dailyWeatherJList.setVisibleRowCount(-1);

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.5;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 0;
    dailyWeatherPanel.add(dailyWeatherJList, c);

    return dailyWeatherPanel;
  }

  public void setWeatherLocation(City city) {
    weatherLocation = city;
  }

  public void updateWeather() {
    if (weatherLocation == null) {
      placeholder.setText("");
    } else {
      placeholder.setText(weatherLocation.getName());
      fetchCurrentWeatherDetail();
      fetchDailyWeatherDetail();
    }
  }

  private void fetchCurrentWeatherDetail() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(OpenWeatherService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    OpenWeatherService service = retrofit.create(OpenWeatherService.class);
    Call<CurrentWeatherDetail> callCurrentWeather = service
        .getCurrentWeatherDetail(weatherLocation.getId(), OpenWeatherService.API_KEY);

    callCurrentWeather.enqueue(new Callback<CurrentWeatherDetail>() {
      @Override
      public void onResponse(Call<CurrentWeatherDetail> call,
          Response<CurrentWeatherDetail> response) {
        if (response.body() != null) {
          updateCurrentWeatherDetailView(response.body());
        }
      }

      @Override
      public void onFailure(Call<CurrentWeatherDetail> call, Throwable t) {

      }
    });
  }

  private void fetchDailyWeatherDetail() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(OpenWeatherService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    OpenWeatherService service = retrofit.create(OpenWeatherService.class);
    Call<DailyWeatherDetail> callDailyWeather = service
        .getDailyWeatherDetail(weatherLocation.getId(), OpenWeatherService.API_KEY);

    callDailyWeather.enqueue(new Callback<DailyWeatherDetail>() {
      @Override
      public void onResponse(Call<DailyWeatherDetail> call,
          Response<DailyWeatherDetail> response) {
        updateDailyWeatherDetailView(response.body());
        System.out.println(response.toString());
      }

      @Override
      public void onFailure(Call<DailyWeatherDetail> call, Throwable t) {
        System.out.println(t.getMessage());
      }
    });
  }

  private void updateCurrentWeatherDetailView(CurrentWeatherDetail currentWeatherDetail) {
    locationLabel.setText(
        currentWeatherDetail.getName() + ", " + currentWeatherDetail.getSys().getCountry());
    temperatureLabel
        .setText(Long.toString(Math.round(currentWeatherDetail.getMain().getTemp() - 273)) + "°");
    weatherDescriptionLabel
        .setText(WordUtils.capitalize(currentWeatherDetail.getWeather().get(0).getDescription()));

    Date date = new Date(Integer.toUnsignedLong(currentWeatherDetail.getDt()) * 1000);
    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    String formattedDate = sdf.format(date);

    timeUpdatedLabel.setText("Diperbaharui pada " + formattedDate);
    ImageIcon imageIcon = new ImageIcon(
        WeatherUtils.getImageResources(currentWeatherDetail.getWeather().get(0).getId()));
    Image image = imageIcon.getImage();
    Image newImg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
    temperatureLabel.setIcon(new ImageIcon(newImg));

    windSpeedLabel.setText(
        "Wind " + currentWeatherDetail.getWind().getSpeed() + " m/s (" + currentWeatherDetail
            .getWind().getDeg() + "°)");
    humidityLabel.setText("Humidity " + currentWeatherDetail.getMain().getHumidity() + "%");
    visibilityLabel.setText("Visibility  " + currentWeatherDetail.getVisibility() / 1000 + " km");
    pressureLabel.setText("Pressure  " + currentWeatherDetail.getMain().getPressure() + " hPa");
  }

  private void updateDailyWeatherDetailView(DailyWeatherDetail dailyWeatherDetail) {
    ArrayList<DailyWeatherSummary> dailyWeatherSummaries = getWeatherSummary(dailyWeatherDetail);
    DefaultListModel defaultListModel = (DefaultListModel) dailyWeatherJList.getModel();
    defaultListModel.removeAllElements();
    for (DailyWeatherSummary summary : dailyWeatherSummaries) {
      defaultListModel.addElement(summary);
    }
    dailyWeatherJList.updateUI();
  }

  private ArrayList<DailyWeatherSummary> getWeatherSummary(DailyWeatherDetail dailyWeatherDetail) {
    ArrayList<DailyWeatherSummary> dailyWeatherSummaries = new ArrayList<>();
    boolean first = true;
    Long highestTemp = null, lowestTemp = null;
    DailyWeatherSummary current = new DailyWeatherSummary();
    HashMap<String, Integer> likelyWeather = new HashMap<>();
    HashMap<String, Integer> weatherAssoc = new HashMap<>();
    for (DailyWeather dailyWeather : dailyWeatherDetail.getList()) {
      if (first) {
        System.out.println(dailyWeather.getDt());
        current.setDateTime(dailyWeather.getDt());
        highestTemp = Math.round(dailyWeather.getMain().getTempMax());
        lowestTemp = Math.round(dailyWeather.getMain().getTempMin());
        first = false;
      } else if (dailyWeather.getDtTxt().split(" ")[1].matches("00:00:00")) {
        current.setDescription(getMostLikelihoodWeatherDesc(likelyWeather));
        current.setTempHigh(highestTemp);
        current.setTempLow(lowestTemp);
        current.setWeatherID(weatherAssoc.get(current.getDescription()));
        dailyWeatherSummaries.add(current);

        current = new DailyWeatherSummary();
        likelyWeather = new HashMap<>();
        weatherAssoc = new HashMap<>();
        current.setDateTime(dailyWeather.getDt());
        System.out.println(dailyWeather.getDt());
      }

      if (dailyWeather.getMain().getTempMax() > highestTemp) {
        highestTemp = Math.round(dailyWeather.getMain().getTempMax());
      }
      if (dailyWeather.getMain().getTempMin() < lowestTemp) {
        lowestTemp = Math.round(dailyWeather.getMain().getTempMin());
      }

      String weatherDescription = dailyWeather.getWeather().get(0).getMain();
      if (!likelyWeather.containsKey(weatherDescription)) {
        likelyWeather.put(weatherDescription, 1);
      } else {
        Integer count = likelyWeather.get(weatherDescription);
        likelyWeather.put(weatherDescription, count + 1);
      }

      if (!weatherAssoc.containsKey(weatherDescription)) {
        weatherAssoc.put(weatherDescription, dailyWeather.getWeather().get(0).getId());
      }
    }

    current.setDescription(getMostLikelihoodWeatherDesc(likelyWeather));
    current.setTempHigh(highestTemp);
    current.setTempLow(lowestTemp);
    current.setWeatherID(weatherAssoc.get(current.getDescription()));
    dailyWeatherSummaries.add(current);

    return dailyWeatherSummaries;
  }

  private String getMostLikelihoodWeatherDesc(HashMap<String, Integer> likelyWeather) {
    Map.Entry<String, Integer> maxEntry = null;
    for (Map.Entry<String, Integer> entry : likelyWeather.entrySet()) {
      if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
        maxEntry = entry;
      }
    }
    return maxEntry != null ? maxEntry.getKey() : null;
  }

}
