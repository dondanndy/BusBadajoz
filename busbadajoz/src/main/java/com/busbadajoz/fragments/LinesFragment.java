package com.busbadajoz.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busbadajoz.R;

public class LinesFragment extends Fragment {

    TextView contador;
    private static final String TAG = "LinesFragment";

    Handler mainHandler = new Handler();

    public LinesFragment() {
        // Required empty public constructor
    }

    public static LinesFragment newInstance() {
        LinesFragment fragment = new LinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_lines, container, false);

        contador = (TextView) fragview.findViewById(R.id.counter);

        ExampleCounter example = new ExampleCounter();
        example.start();

        return fragview;
    }

    public class ExampleCounter extends Thread {
        @Override
        public void run() {
            Handler threadHandler = new Handler(Looper.getMainLooper());
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(2000);
                    threadHandler.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               contador.setText("hola");
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
