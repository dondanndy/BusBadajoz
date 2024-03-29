package com.busbadajoz.models;

import android.util.Pair;

import com.busbadajoz.Utils.Units;

import java.time.Instant;
import java.util.ArrayList;

public class StopModelView implements Cloneable {
    /*
        This class contains a model for the view of the Stop, a bit different from the StopModel for
        the data as we don't need location and with more strings to have everything to display here.
     */

    private String name;
    private ArrayList<BusModelView> buses;

    private int distance;

    private Pair<Integer, Units> updateTime;

    public StopModelView(String name, int dist, int updateTime, Units updateTimeUnits){
        this.name = name;
        this.distance = dist;

        this.updateTime = new Pair<Integer, Units>(updateTime, updateTimeUnits);

        this.buses = new ArrayList<>();
    }

    public StopModelView(String name, int dist, Pair<Integer, Units> updateTime){
        this.name = name;
        this.distance = dist;

        this.updateTime = updateTime;

        this.buses = new ArrayList<>();
    }

    @Override
    public StopModelView clone() throws CloneNotSupportedException {
        StopModelView clone = null;
        try
        {
            clone = (StopModelView) super.clone();

            ArrayList<BusModelView> tmp = new ArrayList<>();

            for (BusModelView bus : this.buses){
                tmp.add(new BusModelView(bus.getLineName(), bus.getTimeLeft(), bus.getDistanceLeft()));
            }

            clone.setBuses(tmp);
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
        return clone;
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

    public Pair<Integer, Units> getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Pair<Integer, Units> updateTime) {
        this.updateTime = updateTime;
    }
}
