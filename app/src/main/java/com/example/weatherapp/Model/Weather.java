package com.example.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("location")
    public Location location;

    @SerializedName("current")
    public Current current;


}
