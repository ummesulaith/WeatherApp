package com.example.weatherapp.View;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.weatherapp.Controller.WeatherApi;
import com.example.weatherapp.Model.Weather;
import com.example.weatherapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class HomeActivity extends AppCompatActivity {
    ImageView img;
    TextView status, ccity, temp,state;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        img = (ImageView) findViewById(R.id.cloud);
        status = (TextView) findViewById(R.id.status);
        ccity = (TextView) findViewById(R.id.tempcity);
        temp = (TextView) findViewById(R.id.temp);
        state = (TextView)findViewById(R.id.tempstate);

        Toast.makeText(getApplicationContext(), "Please Wait while fetching Data", Toast.LENGTH_SHORT).show();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi api = retrofit.create(WeatherApi.class);

        Call<Weather> call = api.getWeather();
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                System.out.println("API HIT" + response.body());


                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: received information: " + response.body().toString());


                Weather weatherResponse = response.body();
                assert weatherResponse != null;
                String c =  weatherResponse.location.name;
                ccity.setText(c);

                String s =  weatherResponse.location.region;
                state.setText(s);

                String t = weatherResponse.current.temperature;
                temp.setText(t);

                ArrayList desc = weatherResponse.current.weather_descriptions;

                String descc = (String) desc.get(0);
                status.setText(descc);


                ArrayList ic = weatherResponse.current.icons;
                String icc = (String) ic.get(0);

                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(icc)
                        .into(img);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                System.out.println("Error" + t.getMessage());
            }
        });
    }
}