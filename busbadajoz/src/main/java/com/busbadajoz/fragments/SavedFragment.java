package com.busbadajoz.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.StopModel;

import com.busbadajoz.Adapters.StopAdapter;

import java.util.ArrayList;


public class SavedFragment extends Fragment {

    /* This fragment is the first of all of them. The user will be able to select several stops as
    "saved", and always see them quickly when they open the app. This fragment shows them.
     */

    private RecyclerView saved_stops_recyclerview;

    private static final String TAG = "SavedFragment";
    private ArrayList<StopModel> paradas_random = new ArrayList<>();

    public SavedFragment() {
        // Required empty public constructor
    }

    public static SavedFragment newInstance() {
        SavedFragment fragment = new SavedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");

        createDummyData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        RecyclerView saved_stops_recyclerview = rootView.findViewById(R.id.saved_recyclerview);

        saved_stops_recyclerview.setHasFixedSize(true);

        saved_stops_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StopAdapter adapter = new StopAdapter(paradas_random, getContext());
        saved_stops_recyclerview.setAdapter(adapter);

        return rootView;
    }

    private void createDummyData() {

        /* For the time being, and just for debugging until the logic to get the data gets implemented
         */

        Log.d(TAG, "createDummyData called");
        paradas_random = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            StopModel stop = new StopModel();
            stop.setName("Parada " + i);
            ArrayList<BusModel> buses = new ArrayList<>();
            for (int j = 1; j <= 6; j++) {
                buses.add(new BusModel("Linea " + j, "" + (2 * j)));
            }
            stop.setAllItemInSection(buses);
            paradas_random.add(stop);
        }

    }
}
