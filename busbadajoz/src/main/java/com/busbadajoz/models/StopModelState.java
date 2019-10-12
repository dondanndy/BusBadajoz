package com.busbadajoz.models;

import android.os.Parcelable;

public class StopModelState {

        /*
            This is the state of every stop. It includes the state of every of the buses, to change
            the color of its background.

            Also saves if one of them has been selected and its position, to display a descriptive
            text below the list.

            And lastly, it saves the LayoutManager state to keep the scrolling state in the buses
            recyclerview.
         */

    private int bus_selected;
    private Parcelable scroll_state;
    private Boolean showDetail;

    public StopModelState(){
        this.bus_selected = -1;
        this.scroll_state = null;
        this.showDetail = false;
    }

    public int getBusSelected() {
        return bus_selected;
    }

    public void setBusSelected(int position) {
        this.bus_selected = position;
    }

    public Parcelable getScrollState() {
        return this.scroll_state;
    }

    public void setScrollState(Parcelable state) {
        this.scroll_state = state;
    }

    public Boolean getShowDetail(){
        return this.showDetail;
    }

    public void setShowDetail(Boolean show_detail) {
        this.showDetail = show_detail;
    }

}
