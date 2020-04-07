package com.perso.model;

import android.media.Image;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    private String wind_speed;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String min_temp;
    private String max_temp;
    private Image icon;

    public Weather(JSONObject weather) throws JSONException {
        weather_state_name = weather.getString("weather_state_name");
        weather_state_abbr = weather.getString("weather_state_abbr");
        wind_direction_compass = weather.getString("wind_direction_compass");
        min_temp = weather.getString("min_temp");
        max_temp = weather.getString("max_temp");
        wind_speed = weather.getString("wind_speed");
        icon = null;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public Image getIcon() {
        return icon;
    }

    public String getWind_speed() {
        return wind_speed;
    }
}
