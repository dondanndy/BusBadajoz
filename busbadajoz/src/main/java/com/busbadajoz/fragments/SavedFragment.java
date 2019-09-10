package com.busbadajoz.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busbadajoz.Data.AppData;
import com.busbadajoz.Data.DataRepository;
import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.StopModel;

import com.busbadajoz.Adapters.StopAdapter;
import com.busbadajoz.models.data.StopMapModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SavedFragment extends Fragment {

    /* This fragment is the first of all of them. The user will be able to select several stops as
    "saved", and always see them quickly when they open the app. This fragment shows them.
     */

    private DataRepository data;

    MutableLiveData<ArrayList<StopModel>> models = new MutableLiveData<ArrayList<StopModel>>();

    volatile boolean stop = false;

    HashMap<String, StopMapModel> map = new AppData().getMap();
    private RecyclerView saved_stops_recyclerview;

    private static final String TAG = "SavedFragment";
    public SavedFragment(DataRepository data) {

        this.data = data;

        ArrayList<StopModel> tmp = new ArrayList<>();

        ArrayList<String> array = new ArrayList<String>();

        array.addAll(Arrays.asList("2", "84", "229", "240", "110", "100"));
        for (int i = 0; i < array.size(); i++ ){
            StopModel tmp2 = new StopModel();
            tmp2.setName("Linea " + this.map.get(array.get(i)).getStopName());
            ArrayList<BusModel> tmp3 = new ArrayList<>();

            for (int j = 0; j < this.map.get(array.get(i)).getStopBuses().length; j++){
                tmp3.add(new BusModel(this.map.get(array.get(i)).getStopBuses()[j][0], "-"));
            }
            tmp2.setAllItemInSection(tmp3);
            tmp.add(tmp2);
        }

        this.models.setValue(tmp);
    }

    public static SavedFragment newInstance(DataRepository data) {
        SavedFragment fragment = new SavedFragment(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.map = ((MainActivity) getActivity()).giveMap().getMap();
        //createDummyData();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.data.getData().observe(this, new Observer<ArrayList<StopModel>>() {
            @Override
            public void onChanged(ArrayList<StopModel> stopModels) {
                Log.d(TAG, "onChanged: SavedFragment called");
                models.setValue(stopModels);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        saved_stops_recyclerview = rootView.findViewById(R.id.saved_recyclerview);

        saved_stops_recyclerview.setHasFixedSize(true);

        saved_stops_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StopAdapter adapter = new StopAdapter(models, getContext(), this);
        saved_stops_recyclerview.setAdapter(adapter);

        return rootView;
    }

    /*
    public void setRecyclerView(){

    }*/


    @Override
    public void onResume() {
        super.onResume();

        this.stop = false;

        //ExampleCounter example = new ExampleCounter();
        //example.start();
    }

    private ArrayList<StopModel> extractTestData() {

        /*
            For the time being, and just for debugging until the logic to get the data gets implemented
         */
        ArrayList<StopModel> paradas_random;


        //Log.d(TAG, "createDummyData called with " + n);
        paradas_random = new ArrayList<>();
        String[] stops_test = {"2", "84", "229", "240", "110", "100"};

        for (String stop_test : stops_test) {
            StopMapModel stop_model = map.get(stop_test);

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

    @Override
    public void onPause() {
        super.onPause();
        stop = true;
        Log.d(TAG, "onDestroy: Called");
    }

}
