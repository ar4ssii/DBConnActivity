package com.example.dbconnactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv1);
        StrictMode.enableDefaults();

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(new URI("http://192.168.100.107/DBConnActivity/connect.php")  );
            HttpResponse response = httpClient.execute(httpPost);
            // Check the status code of the response
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                Toast.makeText(getApplicationContext(), "PHP MSQL CONNECT...", Toast.LENGTH_LONG).show();
                Log.e("Pass 1", "Connection Success");
                tv.setText("Connected Successfully...");
            } else {
                Toast.makeText(getApplicationContext(), "Failed to connect to the database. Status Code: " + statusCode, Toast.LENGTH_LONG).show();
                Log.e("Pass 1", "Connection Failed. Status Code: " + statusCode);
                tv.setText("Connection Failed. Status Code: " + statusCode);
            }
        }catch (Exception e){
            Log.e("Pass 1", e.toString());
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}