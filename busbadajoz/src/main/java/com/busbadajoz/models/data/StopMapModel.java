package com.busbadajoz.models.data;

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
