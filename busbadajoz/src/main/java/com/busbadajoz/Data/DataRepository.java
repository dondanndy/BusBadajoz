package com.busbadajoz.Data;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.StopLiveDataModel;
import com.busbadajoz.models.StopModel;
import com.busbadajoz.models.StopModelView;
import com.busbadajoz.models.StopMapModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class DataRepository {
    /*
        This class is the data repository that gets the info from the internet and feeds it to the
        appropriate ViewModel to show it in a fragment.

        In here we have to separate arrays: one of them contains the static info data refering to the
        stops, and the LiveData one will hold the data we will update on the worker thread.
     */

    private AppData appData = new AppData();
    private HashMap<String, StopMapModel> stops_map;
    private ArrayList<String> savedStopsList = new ArrayList<>();

    public MutableLiveData<ArrayList<StopModelView>> savedStops = new MutableLiveData<>();
    public ArrayList<StopModelView> nearbyStops = new ArrayList<StopModelView>();

    /*
    public MutableLiveData<ArrayList<StopLiveDataModel>> savedStopsData = new MutableLiveData<ArrayList<StopLiveDataModel>>();
    public MutableLiveData<ArrayList<StopLiveDataModel>> nearbyStopsData = new MutableLiveData<ArrayList<StopLiveDataModel>>();

     */
    volatile boolean stop;

    public DataRepository(){

        this.savedStopsList.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));

        this.stops_map = this.appData.getMap();
        ArrayList<StopModelView> tmp = new ArrayList<>();
        for (String item : this.savedStopsList){
            tmp.add(this.stops_map.get(item).asStopModelView());
        }
        this.savedStops.setValue(tmp);
    }

    public DataRepository(ArrayList<String> stops) {

        this.stops_map = this.appData.getMap();
        this.savedStopsList = stops;
    }

    public void setLiveData(){
        ArrayList<StopModelView> tmp = new ArrayList<>();
        for (String item : this.savedStopsList) {
            tmp.add(this.stops_map.get(item).asStopModelView());
        }

        this.savedStops.setValue(tmp);
    }


    /*
    public DataRepository(HashMap<String, StopMapModel> map){

        this.stops_map = map;
        this.stops_list.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));
        ArrayList<StopModel> tmp = new ArrayList<>();
        for (String item : stops_list){
            tmp.add(this.stops_map.get(item).asStopModel());
        }
        this.uiData.setValue(tmp);
    }*/

    public void stopLoop(){
        Log.d("DATA", "stoploop llamado");
        this.stop = true;
    }

    public void initLoop(){
        Log.d("DATA", "initloop llamado");
        this.stop = false;

        DataLoop dataLoop = new DataLoop();
        dataLoop.start();
    }

    public StopModelView getStopInfo(String code){
        return stops_map.get(code).asStopModelView();
    }

    public void setStopsListQuery(ArrayList<String> list){
        this.savedStopsList = list;
    }

    private ArrayList<StopModelView> extractTestData(ArrayList<String> list) {
        /*
            For the time being, and just for debugging until the logic to get the data gets implemented
         */
    ArrayList<StopModelView> paradas_random = new ArrayList<>();

    for (String stop_test : list) {
        StopMapModel stop_model = this.stops_map.get(stop_test);

        StopModelView stop = new StopModelView(stop_model.getStopName(), 33);
        stop.setName(stop_model.getStopName());

        ArrayList<BusModelView> buses = new ArrayList<>();
        for (String[] bus : stop_model.getStopBuses()) {
            buses.add(new BusModelView("Línea " + bus[0],
                    (int) (Math.random() * 10) + 5,
                    "min",
                    3250,
                    "m"));
        }

        stop.setBuses(buses);
        paradas_random.add(stop);
        }

        return paradas_random;
    }

    public MutableLiveData<ArrayList<StopModelView>> getSavedStops(){
        return this.savedStops;
    }

    public class DataLoop extends Thread {
        /*
            Worker thread. In this thread we will fetch the data from the website and parse it.
         */
        @Override
        public void run() {
            Log.d("Worker Thread", "run: Alla vamos");
            //Looper.prepare();
            Handler threadHandler = new Handler(Looper.getMainLooper());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(!stop) {
                try {
                    threadHandler.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               Log.d("WORKER THREAD", "run: Ojo que vienen datos");
                                               savedStops.postValue(extractTestData(savedStopsList));
                                           }
                                       }

                    );
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("WORKER THREAD", "run: Thread stopped");
        }
    }
}
