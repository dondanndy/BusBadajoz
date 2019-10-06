package com.busbadajoz.Data;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.StopModelView;
import com.busbadajoz.models.StopMapModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static androidx.constraintlayout.widget.Constraints.TAG;


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
    public MutableLiveData<ArrayList<StopModelView>> nearbyStops = new MutableLiveData<>();

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

        StopModelView stop = new StopModelView(stop_model.getStopName(), 33, (int) (Math.random() * 10), " minutos");
        stop.setName(stop_model.getStopName());

        ArrayList<BusModelView> buses = new ArrayList<>();
        for (String[] bus : stop_model.getStopBuses()) {

            BusModelView tmp = new BusModelView("Línea " + bus[0],
                    (int) (Math.random() * 12),
                    ((int) (Math.random() * 13) % 2 == 0) ? "minutos": "minuto",
                    String.format("%.2f",(Math.random() * 100)),
                    ((int) (Math.random() * 13) % 2 == 0) ? "metros": "kilom.");
            tmp.setNextStop(new String[]{bus[1], stops_map.get(bus[1]).getStopName()});

            //A lot of temp variables, I know.
            String[] dir = new String[2];
            if (bus[2].equals("1")){
                dir[0] = appData.getFirstStops().get(bus[0])[1];
            } else {
                dir[0] = appData.getFirstStops().get(bus[0])[0];
            }
            dir[1] = stops_map.get(dir[0]).getStopName();

            tmp.setDirection(dir);

            buses.add(tmp);
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
