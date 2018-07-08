package app.weather.daily;

public class DailyWeatherSummary {

  Long tempLow;
  Long tempHigh;
  String description;
  Integer weatherID;
  Integer dateTime;

  public DailyWeatherSummary() {

  }

  public Long getTempLow() {
    return tempLow;
  }

  public void setTempLow(Long tempLow) {
    this.tempLow = tempLow;
  }

  public Integer getWeatherID() {
    return weatherID;
  }

  public void setWeatherID(Integer weatherID) {
    this.weatherID = weatherID;
  }

  public Long getTempHigh() {
    return tempHigh;
  }

  public void setTempHigh(Long tempHigh) {
    this.tempHigh = tempHigh;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getDateTime() {
    return dateTime;
  }

  public void setDateTime(Integer dateTime) {
    this.dateTime = dateTime;
  }
}
