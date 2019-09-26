package com.busbadajoz.models;

import com.busbadajoz.models.BusModel;

import java.util.ArrayList;

public class StopModel {
    //private StopLocation Location;_
    private String name;
    private ArrayList<BusModel> busesOnStop;

    public StopModel() {
            }

    public StopModel(String name) {
            this.name = name;
            }

    public String getName() {
            return name;
            }

    public void setName(String name) {
            this.name = name;
            }

    public ArrayList<BusModel> getAllItemInSection() {
        return busesOnStop;
    }

    public void setAllItemInSection(ArrayList<BusModel> busesOnStop) {
        this.busesOnStop = busesOnStop;
    }
}
