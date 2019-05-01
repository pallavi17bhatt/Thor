package com.example.thor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static final String Weather_data = "Weather_data";

    HashMap<String, String> country = new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country.put("Bengaluru","Bengaluru,ind");
        country.put("Singapore","Singapore,sgp");
        country.put("London","London,uk");
        country.put("Jakarta","Jakarta,idn");
        country.put("Rome","Rome,ita");
        country.put("Paris","Paris,fra");
        setContentView(R.layout.activity_main);
    }

    private void apiCall(String city) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=0d23334866451f6f183851a9931838d7").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("failure:", e.getMessage());
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String data = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       //Toast.makeText(getApplicationContext(),data, Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(getApplicationContext(), City_Weather.class);
                       i.putExtra(Weather_data, data);
                       startActivity(i);
                    }


                });
            }
        });
    }


    public void showWeather(View view) {
       Button b=(Button)view;
        String city = country.get(b.getText().toString());
        apiCall(city);
    }
}
