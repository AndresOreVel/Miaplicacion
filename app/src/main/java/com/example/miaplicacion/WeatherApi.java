package com.example.miaplicacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface WeatherApi {
    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("Barcelona") String location, // Ciudad o coordenadas geográficas
            @Query("062402bc288251c20f1360fd8b0df956") String apiKey // Tu clave de API de OpenWeatherMap
    );
}
