package com.busbadajoz.Data;

import android.os.Handler;
import android.os.Looper;
import android.system.StructPollfd;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.StopModel;
import com.busbadajoz.models.data.StopMapModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class DataRepository {
    /*
        This class is the data repository that gets the info from the internet and feeds it to the
        appropriate ViewModel to show it in a fragment.
     */

    private HashMap<String, StopMapModel> stops_map;
    private ArrayList<String> stops_list = new ArrayList<>();

    public MutableLiveData<ArrayList<StopModel>> uiData = new MutableLiveData<ArrayList<StopModel>>();

    volatile boolean stop;

    public DataRepository(HashMap<String, StopMapModel> map){

        this.stops_map = map;
        this.stops_list.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));
        ArrayList<StopModel> tmp = new ArrayList<>();
        for (String item : stops_list){
            tmp.add(this.stops_map.get(item).asStopModel());
        }
        this.uiData.setValue(tmp);
    }

    public void stopLoop(){
        this.stop = true;
    }

    public void initLoop(){
        Log.d("DATA", "initloop llamado");
        this.stop = false;

        DataLoop dataLoop = new DataLoop();
        dataLoop.start();
    }

    public void setStopsListQuery(ArrayList<String> list){
        this.stops_list = list;
    }

        private ArrayList<StopModel> extractTestData(ArrayList<String> list) {

          /*
                For the time being, and just for debugging until the logic to get the data gets implemented
            */
        ArrayList<StopModel> paradas_random = new ArrayList<>();



        for (String stop_test : list) {
            StopMapModel stop_model = this.stops_map.get(stop_test);

            StopModel stop = new StopModel();
            stop.setName(stop_model.getStopName());

            ArrayList<BusModel> buses = new ArrayList<>();
            for (String[] bus : stop_model.getStopBuses()) {
                buses.add(new BusModel("Línea " + bus[0], String.valueOf((int) (Math.random() * 10) + 5)));
            }
            stop.setAllItemInSection(buses);
            paradas_random.add(stop);
        }

        return paradas_random;
}


    public MutableLiveData<ArrayList<StopModel>> getData(){
        return uiData;
    }

    public class DataLoop extends Thread {
        @Override
        public void run() {
            Log.d("Worker Thread", "run: Alla vamos");
            //Looper.prepare();
            Handler threadHandler = new Handler(Looper.getMainLooper());
            while(!stop) {
                try {
                    threadHandler.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               Log.d("Worker Thread", "run: Ojo que vienen datos");
                                               uiData.postValue(extractTestData(stops_list));
                                           }
                                       }

                    );
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
