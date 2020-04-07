package com.perso.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.perso.ApiUrls;
import com.perso.model.Location;
import com.perso.model.Locations;
import com.perso.model.Weather;
import com.perso.model.Weathers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {
    private RequestQueue queue;
    private Location location;
    private Weathers weathers;
    private TextView tvCity;
    private TextView tvWeather;
    private TextView tvTempMin;
    private TextView tvTempMax;
    private TextView tvWindSpeed;
    private ImageView ivIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        queue = Volley.newRequestQueue(getApplicationContext());
        location = getIntent().getParcelableExtra("location");
        Log.d("TEST_APP_Main2Activity", "location : " + Objects.requireNonNull(location).getTitle());
        initComponents();
        sendLocationWeatherInfoRequest(location);
    }

    private void initComponents(){
        tvCity = findViewById(R.id.tvCity);
        tvCity.setText(location.getTitle());
        tvWeather = findViewById(R.id.tvWeather);
        tvTempMin = findViewById(R.id.tvTempMin);
        tvTempMax = findViewById(R.id.tvTempMax);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        ivIcon = findViewById(R.id.ivIcon);
    }

    private void sendLocationWeatherInfoRequest(Location location) {
        String url = ApiUrls.LOCATION_WEATHER_INFO + location.getWoeid();
        Log.d("TEST_APP", "send search 1 :" + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    weathers = new Weathers(response);
                    Log.d("TEST_APP", "weather 0 : " + weathers.getList().get(0).getWeather_state_name());
                    update();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TEST_APP", Objects.requireNonNull(error.getMessage()));
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void update() {
        Weather weather = weathers.getList().get(0);
        tvWeather.setText(weather.getWeather_state_name());
        tvTempMin.setText(weather.getMin_temp());
        tvTempMax.setText(weather.getMax_temp());
        tvWindSpeed.setText(weather.getWind_speed());
        ivIcon.setImageDrawable(resize(getDrawable(R.drawable.c)));
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 300, 300, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}
