package com.busbadajoz.models;

public class BusModelView {
    /*
        The same way as StopModelView, this model is a view model, that contains everything to display,
        line, time left and units mainly.

        But we also have to display the next stop and direction, with the distance left for the bus
        when we expand the info.
     */

    private String lineName;

    private int timeLeft;
    private String unitTimeLeft;

    private int distanceLeft;
    private String unitDistanceLeft;

    private String[] nextStop;
    private String[] direction;

    public BusModelView(){
        //Everything default
        this.lineName = "";
        this.timeLeft = -1;

        this.distanceLeft = -1;
        this.unitDistanceLeft = "m";

        this.nextStop = new String[2];
        this.direction = new String[2];
    }

    public BusModelView(String line, int timeLeft, String unitTimeLeft, int distanceLeft, String unitDistanceLeft){
        this.lineName = line;
        this.timeLeft = timeLeft;

        this.distanceLeft = distanceLeft;
        this.unitDistanceLeft = unitDistanceLeft;

        this.nextStop = new String[2];
        this.direction = new String[2];
    }

    public String[] getNextStop() {
        return nextStop;
    }

    public void setNextStop(String[] nextStop) {
        this.nextStop = nextStop;
    }

    public String[] getDirection() {
        return direction;
    }

    public void setDirection(String[] direction) {
        this.direction = direction;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getDistanceLeft() {
        return distanceLeft;
    }

    public void setDistanceLeft(int distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public String getUnitTimeLeft() {
        return unitTimeLeft;
    }

    public void setUnitTimeLeft(String unitTimeLeft) {
        this.unitTimeLeft = unitTimeLeft;
    }

    public String getUnitDistanceLeft() {
        return unitDistanceLeft;
    }

    public void setUnitDistanceLeft(String unitDistanceLeft) {
        this.unitDistanceLeft = unitDistanceLeft;
    }
}
