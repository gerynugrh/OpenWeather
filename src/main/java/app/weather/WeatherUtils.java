package app.weather;

public class WeatherUtils {

  public static String getImageResources(Integer weatherCodes) {
    if (weatherCodes / 100 == 2) {
      return "./src/main/resources/weather/008-storm.png";
    } else if (weatherCodes / 100 == 3) {
      return "./src/main/resources/weather/013-rain.png";
    } else if (weatherCodes / 100 == 5) {
      return "./src/main/resources/weather/018-light.png";
    } else if (weatherCodes / 100 == 6) {
      return "./src/main/resources/weather/010-snow.png";
    } else if (weatherCodes / 100 == 7) {
      return "./src/main/resources/weather/001-windy.png";
    } else if (weatherCodes == 800) {
      return "./src/main/resources/weather/007-sun.png";
    } else if (weatherCodes / 100 == 8) {
      return "./src/main/resources/weather/024-cloudy.png";
    }
    return null;
  }
}
