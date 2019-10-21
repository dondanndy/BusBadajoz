package com.busbadajoz.models;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.busbadajoz.Data.DataRepository;

import java.io.IOException;
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
        //fillsavedStops(savedStopsList);

        this.savedStopsData = Transformations.map(dataRepository.getSavedStops(), data -> {
            return data;
        });

        updateCachedData();
    }

    public void fillsavedStops(ArrayList<String> stops){
        dataRepository.setStopsListQuery(stops);
    }

    public void fillNearbyStops(){}

    public StopModelView getStopInfo(String code){
        return dataRepository.getStopInfo(code);
    }

    public void updateCachedData(){
        Log.d(TAG, "updateCachedData: called");
        this.savedStops = dataRepository.getSavedStops().getValue();
    }

    public void initLoop(){
        dataRepository.initLoop();
    }

    public void stopLoop(){
        dataRepository.stopLoop();
    }

    public void getDatafromWeb() throws IOException {
        dataRepository.getDataFromWebAsync("2");
    }

    public ArrayList<StopModelView> getSavedStops() {
        Log.d(TAG, "getSavedStops: method called");
        return this.savedStops;
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
