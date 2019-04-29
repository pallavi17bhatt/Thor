package com.example.thor;

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


public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClicked(View view) {
        apiCall("London,uk");
    }

    private void apiCall(String city) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=0d23334866451f6f183851a9931838d7").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("failure:", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String data = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //changeMessage(data);
                    }
                });
            }
        });
    }

    public void onBanglore(View view) {
        b1=(Button)findViewById(R.id.button1);
        String city = b1.getText().toString();
        city=city.concat(",ind");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();



    }

    public void onSingapore(View view) {
        b2=(Button)findViewById(R.id.button2);
        String city = b2.getText().toString();
        city=city.concat(",sgp");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
    }

    public void onLondon(View view) {
        b3=(Button)findViewById(R.id.button3);
        String city = b3.getText().toString();
        city=city.concat(",uk");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
    }

    public void onJakarta(View view) {
        b4=(Button)findViewById(R.id.button4);
        String city = b4.getText().toString();
        city=city.concat(",idn");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
    }



    public void onRome(View view) {
        b5=(Button)findViewById(R.id.button5);
        String city = b5.getText().toString();
        city=city.concat(",ita");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
    }

    public void onParis(View view) {
        b6=(Button)findViewById(R.id.button6);
        String city = b6.getText().toString();
        city=city.concat(",fra");
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
    }

    /*public void changeMessage (String data) {
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(data);
    }
*/





}
