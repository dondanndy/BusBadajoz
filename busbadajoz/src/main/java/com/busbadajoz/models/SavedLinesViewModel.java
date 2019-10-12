package com.busbadajoz.models;

import android.os.Parcelable;

import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SavedLinesViewModel extends ViewModel {
    private ArrayList<StopModelState> stops = new ArrayList<>();

    private Parcelable recyclerViewState;

    public SavedLinesViewModel(int size) {

        for (int i=0; i < size; i++){
            stops.add(new StopModelState());
        }
    }

    public Parcelable getRecyclerViewState() {
        return recyclerViewState;
    }

    public void setRecyclerViewState(Parcelable recyclerViewState) {
        this.recyclerViewState = recyclerViewState;
    }

    public ArrayList<StopModelState> getStops() {
        return stops;
    }

    public void setStops(ArrayList<StopModelState> stops) {
        this.stops = stops;
    }
}
