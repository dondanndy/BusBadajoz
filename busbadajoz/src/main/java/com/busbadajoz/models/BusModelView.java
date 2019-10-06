package com.busbadajoz.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BusModelView implements Parcelable {
    /*
        The same way as StopModelView, this model is a view model, that contains everything to display,
        line, time left and units mainly.

        But we also have to display the next stop and direction, with the distance left for the bus
        when we expand the info.
     */

    private String lineName;

    private int timeLeft;
    private String unitTimeLeft;

    /*
        The distance left should be a float, but to avoid problems with the parsing to a String (when
        it's really an int for example) we will pass it as a string and do this parsing manually in
        the repository.
     */
    private String distanceLeft;
    private String unitDistanceLeft;

    private String[] nextStop;
    private String[] direction;

    public BusModelView(){
        //Everything default
        this.lineName = "";
        this.timeLeft = -1;
        this.unitTimeLeft = "";

        this.distanceLeft = "-1";
        this.unitDistanceLeft = "m";

        this.nextStop = new String[2];
        this.direction = new String[2];
    }

    public BusModelView(String line, int timeLeft, String unitTimeLeft, String distanceLeft, String unitDistanceLeft){
        this.lineName = line;
        this.timeLeft = timeLeft;
        this.unitTimeLeft = unitTimeLeft;

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

    public String getDistanceLeft() {
        return distanceLeft;
    }

    public void setDistanceLeft(String distanceLeft) {
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

    //Parcelable stuff
    protected BusModelView(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<BusModelView> CREATOR = new Parcelable.Creator<BusModelView>() {
        public BusModelView createFromParcel(Parcel in) {
            return new BusModelView(in);
        }

        public BusModelView[] newArray(int size) {

            return new BusModelView[size];
        }

    };

    private void readFromParcel(Parcel in) {
        this.lineName = in.readString();
        this.timeLeft = in.readInt();
        this.unitTimeLeft = in.readString();

    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lineName);
        dest.writeInt(this.timeLeft);
        dest.writeString(this.unitTimeLeft);
    }
}
