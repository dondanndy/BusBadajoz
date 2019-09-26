package com.busbadajoz.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

    private DataRepository dataRepository;

    private ArrayList<String> savedStopsList;

    private ArrayList<StopModelView> savedStops;
    private ArrayList<StopModelView> nearbyStops;

    private LiveData<ArrayList<StopModelView>> savedStopsData;
    private MutableLiveData<ArrayList<StopModelView>> nearbyStopsData;

    public DataViewModel(){
        //TODO:  fetch savedStop List

        savedStopsList = new ArrayList<>();

        //As a test, provisional:
        this.savedStopsList.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));

        dataRepository = new DataRepository(this.savedStopsList);
        dataRepository.setLiveData();

        fillNearbyStops();
        fillsavedStops(savedStopsList);

        this.savedStopsData = Transformations.map(dataRepository.getSavedStops(), data -> {
            return data;
        });

        /*Propagate observing
        this.savedStops = Transformations.map(dataRepository.getSavedStopsData(), data -> {
            return data;
        });*/

        //dataRepository.setLiveData();

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


    public ArrayList<StopModelView> getSavedStops() {
        return savedStops;
    }

    public void setSavedStops(ArrayList<StopModelView> savedStops) {
        this.savedStops = savedStops;
    }

    public ArrayList<StopModelView> getNearbyStops() {
        return nearbyStops;
    }

    public void setNearbyStops(ArrayList<StopModelView> nearbyStops) {
        this.nearbyStops = nearbyStops;
    }

    public void setSavedStopsList (ArrayList<String> list){
        this.savedStopsList = list;
    }

    public ArrayList<String> getSavedStopsList(){
        return this.savedStopsList;
    }

    public LiveData<ArrayList<StopModelView>> getSavedStopsData() {
        return savedStopsData;
    }

    public void setSavedStopsData(LiveData<ArrayList<StopModelView>> savedStopsData) {
        this.savedStopsData = savedStopsData;
    }
}
