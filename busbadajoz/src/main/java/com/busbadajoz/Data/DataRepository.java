package com.busbadajoz.Data;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.busbadajoz.Network.Stop;
import com.busbadajoz.Network.WebRequestInterface;
import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.StopModelView;
import com.busbadajoz.models.StopMapModel;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DataRepository {
    /*
        This class is the data repository that gets the info from the internet and feeds it to the
        appropriate ViewModel to show it in a fragment.

        In here we have to separate arrays: one of them contains the static info data refering to the
        stops, and the LiveData one will hold the data we will update on the worker thread.
     */

    //private Retrofit retrofit;
    private WebRequestInterface webRequestInterface;

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

        initRetrofit();
    }

    public DataRepository(ArrayList<String> stops) {

        this.stops_map = this.appData.getMap();
        this.savedStopsList = stops;

        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tubasa.autobus.cloud/tiemposdellegada/")
                .addConverterFactory(TikXmlConverterFactory.create())
                .build();

        this.webRequestInterface = retrofit.create(WebRequestInterface.class);
    }

    public void setLiveData(){
        ArrayList<StopModelView> tmp = new ArrayList<>();
        for (String item : this.savedStopsList) {
            tmp.add(this.stops_map.get(item).asStopModelView());
        }

        this.savedStops.setValue(tmp);
    }


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

    public void getDataFromWebAsync(String stopID) throws IOException {

        Log.d(TAG, "getDataFromWebAsync: called");
        
        if (stopID == null) {
            Log.d(TAG, "getDataFromWebAsync: stopID null");
        }

        Call<Stop> call = this.webRequestInterface.getStop(stopID);

        call.enqueue(new Callback<Stop>() {
            @Override
            public void onResponse(Call<Stop> call, Response<Stop> response) {

                Log.d(TAG, "onResponse: called");

                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Bad code");
                    return;
                }

                Log.d(TAG, "getDataFromWeb: Stop received");
                Log.d(TAG, "getDataFromWeb: Stop received, size of lines "+ response.body().lines.size());
                Log.d(TAG, "getDataFromWeb: Stop received, first line: " + response.body().lines.get(0).lineName + " - Time:" + response.body().lines.get(0).timeLeft);

            }

            @Override
            public void onFailure(Call<Stop> call, Throwable t) {
                Log.d(TAG, "onFailure: failed, " + t.getLocalizedMessage());
            }
        });


    }

    public void getDataFromWeb(String stopID) throws IOException {

        Call<Stop> call = this.webRequestInterface.getStop(stopID);

        Stop data = call.execute().body();

        Log.d(TAG, "getDataFromWeb: Stop received");
        Log.d(TAG, "getDataFromWeb: Stop received, size of lines "+ data.lines.size());
        Log.d(TAG, "getDataFromWeb: Stop received, first line: " + data.lines.get(0).lineName + " - Time:" + data.lines.get(0).timeLeft);
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
