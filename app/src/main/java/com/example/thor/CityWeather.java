package com.example.thor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__weather);

        Intent i = getIntent();
        String infoData = i.getStringExtra(MainActivity.info_data);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        WeatherInfo info = gson.fromJson(infoData, WeatherInfo.class);
        String description = info.getWeather()[0].getDescription();
        String cityName = info.getName();
        String speed = Double.toString(info.getWind().getSpeed());
        String visibility = Integer.toString(info.getVisibility());
        String temp = Double.toString(info.getMain().getTemp());

        TextView t = findViewById(R.id.textView);

       t.setText(description + "," + cityName + "," + speed + "," + visibility + "," + temp) ;

    }
}
