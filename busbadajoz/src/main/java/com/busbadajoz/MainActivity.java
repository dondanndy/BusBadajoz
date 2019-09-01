package com.busbadajoz;

import android.content.Intent;
import android.os.Bundle;

import com.busbadajoz.R;
import com.busbadajoz.fragments.SavedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ncapdevi.fragnav.FragNavController;

import com.busbadajoz.fragments.LinesFragment;
import com.busbadajoz.fragments.NearbyFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    final FragNavController fragNavController = new FragNavController(getSupportFragmentManager(), R.id.frag_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        The app is going to have a bottom navigation. The chosen implementation is the FragNav
        library, which lets us control how the navigation stack behaves and controlling the scroll state
        of the fragments, a more complicated approach using Navigation from Jetpack.
         */

        List<Fragment> fragments = startInitialFragments();

        fragNavController.setRootFragments(fragments);

        //Fragments are hidden on switch tab to keep the scroll state.
        fragNavController.setFragmentHideStrategy(FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH);

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);

        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragNavController.switchTab(FragNavController.TAB1);
                        break;
                    case R.id.navigation_lineas:
                        fragNavController.switchTab(FragNavController.TAB2);
                        break;
                    case R.id.navigation_ajustes:
                        fragNavController.switchTab(FragNavController.TAB3);
                        break;
                }
                return true;
            }
        });

        //Settings access
        ImageView settings = findViewById(R.id.settings_access);

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToSettings(v);
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        fragNavController.onSaveInstanceState(outState);
    }

    private void goToSettings(View view){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");

        Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);

    }

    private List<Fragment> startInitialFragments(){

        List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(SavedFragment.newInstance());
        fragments.add(NearbyFragment.newInstance());
        fragments.add(LinesFragment.newInstance());

        return fragments;
    }
}
