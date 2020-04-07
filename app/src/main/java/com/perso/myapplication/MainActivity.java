package com.perso.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.perso.ApiUrls;
import com.perso.model.Location;
import com.perso.model.Locations;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText edtCity;
    private RequestQueue queue;
    private Locations locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(getApplicationContext());
        edtCity = findViewById(R.id.edtCity);
    }

    public void search(View view) {
        sendSearchRequest(edtCity.getText().toString());
        Log.d("TEST_APP", "search");
    }

    private void sendSearchRequest(String city) {
        String url = ApiUrls.SEARCH_LOCATION + city;
        Log.d("TEST_APP", "send search 1 :" + url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TEST_APP", "send search 2");
                try {
                    locations = new Locations(response);
                    Log.d("TEST_APP", "send search 3 : " + locations.getList().get(0).getTitle());
                    if (locations.isSingle())
                        goNextActivity(locations.getFirst());
                    else if (locations.isNotEmpty())
                        selectOne();
                    else
                        notFound();
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
        queue.add(jsonArrayRequest);
    }

    private void selectOne() {
        Toast.makeText(getApplicationContext(), "not single location", Toast.LENGTH_LONG).show();
    }

    private void notFound() {
        Toast.makeText(getApplicationContext(), "location not found", Toast.LENGTH_LONG).show();
    }

    private void goNextActivity(Location location) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("location", location);
        Log.d("TEST_APP", "location : " + location.getTitle());
        startActivity(intent);
    }
}
