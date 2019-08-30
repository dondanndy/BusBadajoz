package com.busbadajoz.models;

import javax.xml.datatype.Duration;

public class BusModel {
    private String line;
    //private Duration time_left;

    private String time_left;
    public BusModel() {

    }

    public BusModel(String line, String time_left) {
        this.line = line;
        this.time_left = time_left;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String name) {
        this.line = line;
    }

    public String getTimeLeft() {
        return time_left;
    }

    public void setTimeLeft(String time_left) {
        this.time_left = time_left;
    }
}