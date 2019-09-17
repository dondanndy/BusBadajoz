package com.busbadajoz.models;

import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.busbadajoz.Data.DataRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class DataViewModel extends ViewModel {
    /*
        This ViewModel will contain the data to display on the Saved and Nearby Fragments.
     */

    private String TAG = "DataViewModel";

    //Observer<ArrayList<StopModelView>> dataObserver;

    private DataRepository dataRepository;

    private ArrayList<String> savedStopsList = new ArrayList<>();

    private LiveData<ArrayList<StopModelView>> savedStops;
    private LiveData<ArrayList<StopModelView>> nearbyStops;

    public DataViewModel(){
        //TODO:  fetch savedStop List

        dataRepository = new DataRepository();

        //As a test, provisional:
        //this.savedStopsList.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));

        fillNearbyStops();
        fillsavedStops(savedStopsList);

        //Propagate observing
        savedStops = Transformations.map(dataRepository.getSavedStopsData(), data -> {
            return data;
        });

        /*
        //Set observing
        dataObserver = new Observer<ArrayList<StopModelView>>() {
            @Override
            public void onChanged(ArrayList<StopModelView> stopModels) {
                Log.d(TAG, "onChanged: SavedFragment called");
                savedStops.setValue(stopModels);
            }
        };

        this.dataRepository.getSavedStopsData().observeForever(dataObserver);*/
    }

    public void fillsavedStops(ArrayList<String> stops){
        dataRepository.setStopsListQuery(stops);

    }

    public void fillNearbyStops(){}

    public StopModelView getStopInfo(String code){
        return dataRepository.getStopInfo(code);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        //As we set observeForever at onCreate(), we need to cancel the subscription when we exit the app
        //this.dataRepository.getSavedStopsData().removeObserver(dataObserver);
    }

    public void initLoop(){
        dataRepository.initLoop();
    }

    public void stopLoop(){
        dataRepository.stopLoop();
    }


    public LiveData<ArrayList<StopModelView>> getSavedStops() {
        return savedStops;
    }

    public void setSavedStops(LiveData<ArrayList<StopModelView>> savedStops) {
        this.savedStops = savedStops;
    }

    public LiveData<ArrayList<StopModelView>> getNearbyStops() {
        return nearbyStops;
    }

    public void setNearbyStops(LiveData<ArrayList<StopModelView>> nearbyStops) {
        this.nearbyStops = nearbyStops;
    }
}
