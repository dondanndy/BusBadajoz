package com.busbadajoz.models;

import java.util.ArrayList;

public class StopLiveDataModel {
    private int updatedTime;
    private ArrayList<BusLiveDataModel> busesData;

    public StopLiveDataModel(){
        updatedTime = -1;
        busesData = new ArrayList<BusLiveDataModel>();
    }


    public int getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(int updatedTime) {
        this.updatedTime = updatedTime;
    }

    public ArrayList<BusLiveDataModel> getBusesData() {
        return busesData;
    }

    public void setBusesData(ArrayList<BusLiveDataModel> busesData) {
        this.busesData = busesData;
    }
}
