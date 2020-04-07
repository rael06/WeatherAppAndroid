package com.perso.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Weathers {
    private List<Weather> list = new ArrayList<>();

    public Weathers(JSONObject response) throws JSONException {
        fillList(response.getJSONArray("consolidated_weather"));
    }

    private void fillList(JSONArray weathers) throws JSONException {
        for(int i=0; i<weathers.length();i++){
            list.add(new Weather((JSONObject) weathers.get(i)));
        }
    }

    public List<Weather> getList() {
        return list;
    }
}
