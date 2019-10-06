package com.busbadajoz.models;

import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.StopModel;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class StopMapModel{
    private String name;
    private float[] location;
    //First index means line, second means, in order, name, next stop and direction.
    private String[][] buses;

    public StopMapModel(String name, String[][] buses, float[] location){
        this.name = name;
        this.buses = buses;
        this.location = location;
    }

    public String getStopName() {
        return name;
    }

    public void setStopName(String name) {
        this.name = name;
    }

    public float[] getStopLocation() {
        return location;
    }

    public void setStopLocation(float[] location) {
        this.location = location;
    }

    public String[][] getStopBuses() {
        return buses;
    }

    public void setStopBuses(String[][] buses) {
        this.buses = buses;
    }

    public StopModel asStopModel(){
        StopModel tmp =  new StopModel();
        tmp.setName(this.name);

        ArrayList<BusModel> tmp2 = new ArrayList();
        for (int i=0; i < this.buses.length; i++){
            tmp2.add(new BusModel(buses[i][0], "-1"));
        }
        tmp.setAllItemInSection(tmp2);

        return tmp;
    }

    public StopModelView asStopModelView(){
        StopModelView tmp =  new StopModelView(this.name, -1, 12, " minutos");

        ArrayList<BusModelView> tmp2 = new ArrayList();
        for (int i=0; i < this.buses.length; i++){
            tmp2.add(new BusModelView("Línea "+this.buses[i][0], -1, "minutos", "-1","m"));
        }
        tmp.setBuses(tmp2);

        return tmp;
    }
}
/*
public class StopMapModel{
    private String name;
    private float[] location;
    private String[][] buses_legacy;
    private ArrayList<ArrayList<String>> buses;

    public StopMapModel(String name, String[][] buses, float[] location){

        Log.d(TAG, "StopMapModel: Aqui");
        this.name = name;
        this.buses_legacy = buses;
        for (String[] item : buses){
            ArrayList<String> tmp = new ArrayList<>();
            for(String item2 : item){
                tmp.add(item2);
            }
            this.buses.add(tmp);
        }
        this.location = location;
    }

    public String getStopName() {
        return name;
    }

    public void setStopName(String name) {
        this.name = name;
    }

    public float[] getStopLocation() {
        return location;
    }

    public void setStopLocation(float[] location) {
        this.location = location;
    }

    public ArrayList<ArrayList<String>> getStopBuses() {
        return buses;
    }

    public String[][] getStopBuses_2() {
        return buses_legacy;
    }

    public void setStopBuses(ArrayList<ArrayList<String>> buses) {
        this.buses = buses;
    }
}*/
