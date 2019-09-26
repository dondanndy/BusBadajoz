package com.busbadajoz.models;

public class BusLiveDataModel {

    /*
        This model contains the data that will be updating in the recyclerview: time and distance left
        with their units, as well as the line name just in case we have to reorder the lines.
     */

    private String lineName;
    private int timeLeft;

    private int distanceLeft;
    private String unitDistanceLeft;

    private String unitsTimeLeft;

    public BusLiveDataModel(){
        this.lineName = "";
        this.timeLeft = -1;
        this.unitsTimeLeft = "-";

        this.distanceLeft = -1;
        this.unitDistanceLeft = "";
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getUnitsTimeLeft() {
        return unitsTimeLeft;
    }

    public void setUnitsTimeLeft(String unitsTimeLeft) {
        this.unitsTimeLeft = unitsTimeLeft;
    }

    public int getDistanceLeft() {
        return distanceLeft;
    }

    public void setDistanceLeft(int distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public String getUnitDistanceLeft() {
        return unitDistanceLeft;
    }

    public void setUnitDistanceLeft(String unitDistanceLeft) {
        this.unitDistanceLeft = unitDistanceLeft;
    }
}
