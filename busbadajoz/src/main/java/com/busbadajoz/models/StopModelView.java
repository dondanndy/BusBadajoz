package com.busbadajoz.models;

import java.time.Instant;
import java.util.ArrayList;

public class StopModelView {
    /*
        This class contains a model for the view of the Stop, a bit different from the StopModel for
        the data as we don't need location and with more strings to have everything to display here.
     */

    private String name;
    private ArrayList<BusModelView> buses;

    private int distance;
    private Instant updatedTime;

    public StopModelView(String name, int dist){
        this.name = name;
        this.distance = dist;

        this.buses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BusModelView> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<BusModelView> buses) {
        this.buses = buses;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }
}
