package com.example.weatherapp.Controller;
import com.example.weatherapp.Model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String BASE_URL ="http://api.weatherstack.com/";
    @GET("current?access_key=e13b0733eba124c0fbfbed4a45468b02&&query=Bangalore")
    Call<Weather> getWeather();
}