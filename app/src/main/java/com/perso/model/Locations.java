package com.perso.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Locations {
    private boolean notEmpty;
    private List<Location> list = new ArrayList<>();
    private boolean single;

    public Locations(JSONArray response) throws JSONException {
        notEmpty = response.length() > 0;
        single = response.length() == 1;
        if(notEmpty)
            fillList(response);
    }

    private void fillList(JSONArray response) throws JSONException {
        for (int i = 0 ; i<response.length(); i++){
            list.add(new Location(response.getJSONObject(i)));
        }
    }

    public boolean isNotEmpty() {
        return notEmpty;
    }

    public List<Location> getList() {
        return list;
    }

    public Location getFirst() {
        return list.get(0);
    }

    public boolean isSingle() {
        return single;
    }
}
