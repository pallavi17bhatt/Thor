package com.example.thor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class City_Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__weather);
        Intent i = getIntent();
        String Weather_data = i.getStringExtra(MainActivity.Weather_data);
        TextView t = findViewById(R.id.textView);
        t.setText(Weather_data);

    }
}
