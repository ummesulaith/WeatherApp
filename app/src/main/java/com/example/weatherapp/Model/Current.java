package com.example.weatherapp.Model;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Current {

    @SerializedName("temperature")
    public String temperature;


    @SerializedName("weather_icons")
    public ArrayList<String> icons;

    @SerializedName("weather_descriptions")
    public ArrayList<String> weather_descriptions;


}