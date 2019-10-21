package com.busbadajoz.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import com.busbadajoz.Utils.Units;

public class BusModelView implements Parcelable {
    /*
        The same way as StopModelView, this model is a view model, that contains everything to display,
        line, time left and units mainly.

        But we also have to display the next stop and direction, with the distance left for the bus
        when we expand the info.
     */

    private String lineName;

    private Pair<Integer, Units> timeLeft;

    /*
        The distance left should be a float, but to avoid problems with the parsing to a String (when
        it's really an int for example) we will pass it as a string and do this parsing manually in
        the repository.
     */


    private Pair<String, Units> distanceLeft;

    private String[] nextStop;
    private String[] direction;

    public BusModelView(){
        //Everything default
        this.lineName = "";
        this.timeLeft = new Pair<Integer, Units>(-1, Units.MINUTE);

        this.distanceLeft = new Pair<String, Units>("-1", Units.METER);

        this.nextStop = new String[2];
        this.direction = new String[2];
    }

    public BusModelView(String line, Pair<Integer, Units> timeLeft, Pair<String, Units> distanceLeft){
        this.lineName = line;

        this.timeLeft = timeLeft;

        this.distanceLeft = distanceLeft;

        this.nextStop = new String[2];
        this.direction = new String[2];
    }

    public BusModelView(String line, int timeLeft, Units unitTimeLeft, String distanceLeft, Units unitDistanceLeft){
        this.lineName = line;

        this.timeLeft = new Pair<Integer, Units>(timeLeft, unitTimeLeft);

        this.distanceLeft = new Pair<String, Units>(distanceLeft, unitDistanceLeft);

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

    public Pair<Integer, Units> getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Pair<Integer, Units> timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Pair<String, Units> getDistanceLeft() {
        return distanceLeft;
    }

    public void setDistanceLeft(Pair<String, Units> distanceLeft) {
        this.distanceLeft = distanceLeft;
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
        this.timeLeft = new Pair<Integer, Units>(in.readInt(), (Units) in.readSerializable());

    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        /*
            Ideally we would be able to bundle the pair, but this should work.
         */
        dest.writeString(this.lineName);
        dest.writeInt(this.timeLeft.first);
        dest.writeSerializable(this.timeLeft.second);
    }
}
