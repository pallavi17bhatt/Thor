package com.example.thor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClicked(View view) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://worldclockapi.com/api/json/est/now").build();
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
                        changeMessage(data);
                    }
                });
            }
        });
    }

    public void changeMessage (String data) {
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(data);
    }




}
