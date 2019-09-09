package com.busbadajoz.models.data;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class StopMapModel{
    private String stop_name;
    private float[] stop_location;
    private String[][] stop_buses;

    public StopMapModel(String name, String[][] buses, float[] location){
        this.stop_name = name;
        this.stop_buses = buses;
        this.stop_location = location;
    }

    public String getStopName() {
        return stop_name;
    }

    public void setStopName(String stop_name) {
        this.stop_name = stop_name;
    }

    public float[] getStopLocation() {
        return stop_location;
    }

    public void setStopLocation(float[] stop_location) {
        this.stop_location = stop_location;
    }

    public String[][] getStopBuses() {
        return stop_buses;
    }

    public void setStopBuses(String[][] stop_buses) {
        this.stop_buses = stop_buses;
    }
}
/*
public class StopMapModel{
    private String stop_name;
    private float[] stop_location;
    private String[][] stop_buses_legacy;
    private ArrayList<ArrayList<String>> stop_buses;

    public StopMapModel(String name, String[][] buses, float[] location){

        Log.d(TAG, "StopMapModel: Aqui");
        this.stop_name = name;
        this.stop_buses_legacy = buses;
        for (String[] item : buses){
            ArrayList<String> tmp = new ArrayList<>();
            for(String item2 : item){
                tmp.add(item2);
            }
            this.stop_buses.add(tmp);
        }
        this.stop_location = location;
    }

    public String getStopName() {
        return stop_name;
    }

    public void setStopName(String stop_name) {
        this.stop_name = stop_name;
    }

    public float[] getStopLocation() {
        return stop_location;
    }

    public void setStopLocation(float[] stop_location) {
        this.stop_location = stop_location;
    }

    public ArrayList<ArrayList<String>> getStopBuses() {
        return stop_buses;
    }

    public String[][] getStopBuses_2() {
        return stop_buses_legacy;
    }

    public void setStopBuses(ArrayList<ArrayList<String>> stop_buses) {
        this.stop_buses = stop_buses;
    }
}*/
