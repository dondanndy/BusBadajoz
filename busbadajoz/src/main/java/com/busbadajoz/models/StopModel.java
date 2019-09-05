package com.busbadajoz.models;

import com.busbadajoz.models.BusModel;

import java.util.ArrayList;

public class StopModel {
    //private StopLocation Location;_
    private String name;
    private ArrayList<BusModel> buses_on_stop;

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
        return buses_on_stop;
    }

    public void setAllItemInSection(ArrayList<BusModel> buses_on_stop) {
        this.buses_on_stop = buses_on_stop;
    }
}
