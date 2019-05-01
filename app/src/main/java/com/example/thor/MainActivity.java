package com.example.thor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public static final String Weather_data = "Weather_data";
    public static final String description_data = "description_data";
    public static final String temp_data = "temp_data";
    public static final String visible_data = "visible_data";
    public static final String speed_data = "speed_data";

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
                String data = response.body().string();
                try {
                    JSONObject json = new JSONObject(data);
                    String cityName = json.get("name").toString();
                    JSONArray weather = json.getJSONArray("weather");
                    String description = weather.getJSONObject(0).get("description").toString();
                    JSONObject main = json.getJSONObject("main");
                    String temp = main.get("temp").toString();
                    String visibility = json.get("visibility").toString();
                    JSONObject wind = json.getJSONObject("wind");
                    String speed = wind.get("speed").toString();

                    Intent i = new Intent(getApplicationContext(), CityWeather.class);
                    i.putExtra(Weather_data, cityName);
                    i.putExtra(description_data, description);
                    i.putExtra(temp_data, temp);
                    i.putExtra(visible_data, visibility);
                    i.putExtra(speed_data, speed);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void showWeather(View view) {
       Button b=(Button)view;
        String city = country.get(b.getText().toString());
        apiCall(city);
    }
}
