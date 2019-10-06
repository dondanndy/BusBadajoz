package com.busbadajoz.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.busbadajoz.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import com.busbadajoz.models.DataViewModel;

public class NearbyFragment extends Fragment {

    TickerView tickerView;

    //DataViewModel viewModel;

    public NearbyFragment() {
        // Required empty public constructor
    }

    public static NearbyFragment newInstance() {
       NearbyFragment fragment = new NearbyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nearby, container, false);

        /*
        tickerView = rootView.findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setText(String.valueOf(viewModel.getCont()));

        Button test = rootView.findViewById(R.id.test_button);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newValue = Integer.parseInt(tickerView.getText()) + 1;
                tickerView.setText(String.valueOf(newValue));
                //viewModel.setCont(newValue);
            }
        });*/

        //ExampleCounter example = new ExampleCounter();
        //example.start();

        return rootView;
    }

    public class ExampleCounter extends Thread {
        @Override
        public void run() {
            Handler threadHandler = new Handler(Looper.getMainLooper());
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(500);
                    threadHandler.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               int cont = Integer.parseInt(tickerView.getText());
                                               cont++;
                                               tickerView.setText(String.valueOf(cont));
                                           }
                                       }
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
