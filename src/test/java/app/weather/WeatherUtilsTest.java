package app.weather;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherUtilsTest {

  @Test
  public void getImageResources() {
    Integer weatherID = 800;
    assertEquals("./src/main/resources/weather/007-sun.png", WeatherUtils.getImageResources(weatherID));

    Integer outOfBoundWeatherID = 100000;
    assertNull(WeatherUtils.getImageResources(outOfBoundWeatherID));
  }
}