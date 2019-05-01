package com.example.thor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__weather);
        Intent i = getIntent();
        String weather_data = i.getStringExtra(MainActivity.Weather_data);
        String description = i.getStringExtra(MainActivity.description_data);
        String speed = i.getStringExtra(MainActivity.speed_data);
        String visibility = i.getStringExtra(MainActivity.visible_data);
        String temp = i.getStringExtra(MainActivity.temp_data);
        TextView t = findViewById(R.id.textView);

        t.setText(description + "," + weather_data + "," + speed + "," + visibility + "," + temp) ;

    }
}
