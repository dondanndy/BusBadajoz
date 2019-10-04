package com.busbadajoz.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busbadajoz.Adapters.BusDiffUtilCallback;
import com.busbadajoz.Adapters.StopDiffUtilCallback;
import com.busbadajoz.Data.AppData;
import com.busbadajoz.Data.DataRepository;
import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.DataViewModel;
import com.busbadajoz.models.DataViewModelFactory;
import com.busbadajoz.models.StopModel;

import com.busbadajoz.Adapters.StopAdapter;
import com.busbadajoz.models.StopMapModel;
import com.busbadajoz.models.StopModelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SavedFragment extends Fragment {

    /* This fragment is the first of all of them. The user will be able to select several stops as
    "saved", and always see them quickly when they open the app. This fragment shows them.
     */


    private DataViewModel dataModel;
    private RecyclerView saved_stops_recyclerview;

    private static final String TAG = "SavedFragment";

    public SavedFragment() {}

    public static SavedFragment newInstance() {
        return new SavedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataModel =  ViewModelProviders.of(getActivity(), new DataViewModelFactory()).get(DataViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        saved_stops_recyclerview = rootView.findViewById(R.id.saved_recyclerview);

        saved_stops_recyclerview.setHasFixedSize(true);

        saved_stops_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StopAdapter adapter = new StopAdapter(dataModel, getContext(), dataModel.getSavedStops());
        saved_stops_recyclerview.setAdapter(adapter);

        this.dataModel.getSavedStopsData().observe(this, new Observer<ArrayList<StopModelView>>() {
            @Override
            public void onChanged(ArrayList<StopModelView> stopModels) {
                adapter.updateData(stopModels);
            }
        });

        return rootView;
    }
}
