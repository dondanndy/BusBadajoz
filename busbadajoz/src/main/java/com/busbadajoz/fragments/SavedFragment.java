package com.busbadajoz.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busbadajoz.R;
import com.busbadajoz.models.DataViewModel;
import com.busbadajoz.models.DataViewModelFactory;
import com.busbadajoz.models.SavedLinesViewModel;

import com.busbadajoz.Adapters.StopAdapter;
import com.busbadajoz.models.SavedLinesViewModelFactory;
import com.busbadajoz.models.StopModelState;
import com.busbadajoz.models.StopModelView;

import java.util.ArrayList;


public class SavedFragment extends Fragment {

    /*
    This fragment is the first of all of them. The user will be able to select several stops as
    "saved", and always see them quickly when they open the app. This fragment shows them.
     */


    private DataViewModel dataModel;
    private SavedLinesViewModel savedLinesViewModel;
    private RecyclerView savedStopsRecyclerview;

    private static final String TAG = "SavedFragment";

    public SavedFragment() {}

    public static SavedFragment newInstance() {
        return new SavedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataModel =  ViewModelProviders.of(getActivity(), new DataViewModelFactory()).get(DataViewModel.class);
        savedLinesViewModel = ViewModelProviders.of(getActivity(), new SavedLinesViewModelFactory(dataModel.getSavedStops().size())).get(SavedLinesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        savedStopsRecyclerview = rootView.findViewById(R.id.saved_recyclerview);

        savedStopsRecyclerview.setHasFixedSize(true);

        savedStopsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        StopAdapter adapter = new StopAdapter(dataModel, getContext(), dataModel.getSavedStops(), savedLinesViewModel.getStops());
        savedStopsRecyclerview.setAdapter(adapter);

        //Save the scroll state when the user stops scrolling.
        RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    savedLinesViewModel.setRecyclerViewState(savedStopsRecyclerview.getLayoutManager().onSaveInstanceState());
                }
            }
        };
        savedStopsRecyclerview.addOnScrollListener(mListener);

        //Observe the data from the dataModel, and from the repository.
        this.dataModel.getSavedStopsData().observe(this, new Observer<ArrayList<StopModelView>>() {
            @Override
            public void onChanged(ArrayList<StopModelView> stopModels) {
                adapter.updateData(stopModels);
            }
        });

        return rootView;
    }
}
