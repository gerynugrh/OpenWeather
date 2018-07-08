package app.weather;

import app.weather.current.CurrentWeatherDetail;
import app.weather.daily.DailyWeatherDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

  String BASE_URL = "https://api.openweathermap.org/";
  String API_KEY = "6e1cd0f9b0a3d2d83302dab67cbb210c";

  @GET("data/2.5/weather")
  Call<CurrentWeatherDetail> getCurrentWeatherDetail(@Query("id") Integer id,
      @Query("appid") String apiKey);

  @GET("data/2.5/forecast")
  Call<DailyWeatherDetail> getDailyWeatherDetail(@Query("id") Integer id,
      @Query("appid") String apiKey);

}
