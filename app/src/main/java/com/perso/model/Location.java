package com.perso.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Location implements Parcelable{
    private String title;
    private String location_type;
    private int woeid = -1;
    private String latt_long;

    public Location(JSONObject response) {
        Log.d("TEST_APP", "test");
        try {
            title = response.getString("title");
            location_type = response.getString("location_type");
            woeid = response.getInt("woeid");
            latt_long = response.getString("latt_long");
        } catch (JSONException e) {
            Log.d("TEST_APP", Objects.requireNonNull(e.getMessage()));
        }
    }

    public String getTitle() {
        return title;
    }

    public String getLocation_type() {
        return location_type;
    }

    public int getWoeid() {
        return woeid;
    }

    public String getLatt_long() {
        return latt_long;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(location_type);
        dest.writeInt(woeid);
        dest.writeString(latt_long);
    }

    public Location(Parcel in) {
        title = in.readString();
        location_type = in.readString();
        woeid = in.readInt();
        latt_long = in.readString();
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
