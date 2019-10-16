package com.busbadajoz.Adapters;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busbadajoz.R;

import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.DataViewModel;
import com.busbadajoz.models.StopModelState;
import com.busbadajoz.models.StopModelView;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder>{

    /*
        Here we need to save the state of every stop of the list to restore them when scrolling
        as the adapter reuses the view set before ignoring the previous state.

        For that we are going to keep a list of a state structure, restoring the stop state to
        the one
     */

    final private String TAG = "StopAdapter";

    private DataViewModel dataModel;

    private ArrayList<StopModelView> stopsModels;

    private ArrayList<StopModelState> stopStates;

    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public StopAdapter(DataViewModel dataModel, Context mContext, ArrayList<StopModelView> data, ArrayList<StopModelState> states) {
        this.mContext = mContext;

        this.dataModel = dataModel;
        this.stopsModels = data;
        this.stopStates = states;

        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    @NonNull
    public StopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_layout, parent, false);
        StopViewHolder rowHolder = new StopViewHolder(v);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(final StopViewHolder holder, final int position) {

        holder.name.setText(this.dataModel.getStopInfo(this.dataModel.getSavedStopsList().get(position)).getName());
        holder.distance.setText("13 km");

        //It looks like we can't set a font from xml so we need this
        //From https://github.com/robinhood/ticker/issues/92#issuecomment-503526527
        Typeface fontFace = ResourcesCompat.getFont(mContext, R.font.lato);
        holder.updateTime.setTypeface(fontFace);
        holder.updateTimeUnits.setTypeface(fontFace);

        fontFace = ResourcesCompat.getFont(mContext, R.font.lato_light);
        holder.distanceLeft.setTypeface(fontFace);


        /*
         ----------- Buses RecyclerView Setup -----------------------------------------------
         */

        //Interface for bus tap notification.
        BusAdapter.BusAdapterInterface adapterInterface = new BusAdapter.BusAdapterInterface() {
            @Override
            public void OnItemClicked(int item_id, int busSelected) {
                stopStates.get(position).setScrollState(holder.recyclerView.getLayoutManager().onSaveInstanceState());

                if (busSelected != -1) {
                    holder.nextStop.setText(stopsModels.get(position).getBuses().get(busSelected).getNextStop()[1]);
                    holder.directionStop.setText(stopsModels.get(position).getBuses().get(busSelected).getDirection()[1]);

                    holder.distanceLeft.setText(stopsModels.get(position).getBuses().get(busSelected).getDistanceLeft());
                    holder.distanceLeftUnits.setText(stopsModels.get(position).getBuses().get(busSelected).getUnitDistanceLeft());
                }

                if (item_id == stopStates.get(position).getBusSelected()){
                    collapse(holder.detail);
                    stopStates.get(position).setBusSelected(-1);
                    stopStates.get(position).setShowDetail(false);
                } else {
                    if(stopStates.get(position).getBusSelected() == -1) {
                        expand(holder.detail);
                        stopStates.get(position).setShowDetail(true);
                    }
                    stopStates.get(position).setBusSelected(item_id);
                }
            }
        };

        //Passing the data to the adapter
        holder.busAdapter = new BusAdapter(this.stopsModels.get(position).getBuses(), stopStates.get(position).getBusSelected(),
                this.dataModel.getStopInfo(this.dataModel.getSavedStopsList().get(position)).getBuses().size(),
                mContext, adapterInterface);

        //Layout and Adapter setup
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(holder.busAdapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);

        //Scroll state listener to save the view when the user stops scrolling.
        RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    stopStates.get(position).setScrollState(holder.recyclerView.getLayoutManager().onSaveInstanceState());
                }
            }
        };

        holder.recyclerView.addOnScrollListener(mListener);

        //Restore of the scroll state when the view is recreated.
        if (stopStates.get(position).getScrollState() != null) {
            holder.recyclerView.getLayoutManager().onRestoreInstanceState(stopStates.get(position).getScrollState());
        }

        //Restore detailed info when the view is recreated.
        if (stopStates.get(position).getShowDetail()){
            holder.detail.setVisibility(View.VISIBLE);
        } else {
            holder.detail.setVisibility(View.GONE);
        }

        int busSelected = this.stopStates.get(position).getBusSelected();
        if (busSelected != -1) {
            holder.nextStop.setText(stopsModels.get(position).getBuses().get(busSelected).getNextStop()[1]);
            holder.directionStop.setText(stopsModels.get(position).getBuses().get(busSelected).getDirection()[1]);

            holder.distanceLeft.setText(stopsModels.get(position).getBuses().get(busSelected).getDistanceLeft());
            holder.distanceLeftUnits.setText(stopsModels.get(position).getBuses().get(busSelected).getUnitDistanceLeft());
        }
    }


    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("DISTANCE")) {
                    holder.distance.setText(String.valueOf((int) o.get(key)) + " km");
                }

                if (key.equals("UPDATE_TIME")) {
                    //Update the time
                    holder.updateTime.setAnimationDuration(400);
                    holder.updateTime.setText(String.valueOf((int) o.get(key)));
                    holder.updateTime.setAnimationDuration(0);
                }

                if (key.equals("UPDATE_UNITS")) {
                    //Update the time units
                    holder.updateTimeUnits.setAnimationDuration(400);
                    holder.updateTimeUnits.setText((String) o.get(key));
                    holder.updateTimeUnits.setAnimationDuration(0);
                }

                //Variable for convenience
                int busSelected = stopStates.get(position).getBusSelected();
                if (busSelected != -1){
                    holder.distanceLeft.setAnimationDuration(400);
                    holder.distanceLeft.setText(String.valueOf(((ArrayList<BusModelView>) o.get("BUSES")).get(busSelected).getDistanceLeft()));
                    holder.distanceLeft.setAnimationDuration(0);

                    holder.distanceLeftUnits.setAnimationDuration(400);
                    holder.distanceLeftUnits.setText(String.valueOf(((ArrayList<BusModelView>) o.get("BUSES")).get(busSelected).getUnitDistanceLeft()));
                    holder.distanceLeftUnits.setAnimationDuration(0);
                }

                if (key.equals("BUSES")) {
                    //Update the buses recyclerview with another DiffUtil
                    holder.busAdapter.updateData((ArrayList<BusModelView>) o.get(key));
                }
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataModel.getSavedStopsList().size();
    }

    public void updateData(ArrayList<StopModelView> newData){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new StopDiffUtilCallback(this.stopsModels, newData));
        diffResult.dispatchUpdatesTo(this);

        this.stopsModels = newData;
    }

    public class StopViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView distance;
        protected RecyclerView recyclerView;
        protected BusAdapter busAdapter;
        protected LinearLayout detail;

        TickerView updateTime;
        TickerView updateTimeUnits;

        TickerView distanceLeft;
        TickerView distanceLeftUnits;

        protected TextView nextStop;
        protected TextView directionStop;
        
        protected ConstraintLayout stop_layout;

        public StopViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.recyclerView = itemView.findViewById(R.id.bus_list);
            this.distance = itemView.findViewById(R.id.distance);

            this.stop_layout = itemView.findViewById(R.id.stop_layout);
            this.updateTime = itemView.findViewById(R.id.updated_time);
            this.updateTimeUnits = itemView.findViewById(R.id.updated_time_units);

            this.detail = itemView.findViewById(R.id.detail_layout);
            this.distanceLeft = itemView.findViewById(R.id.estimate_distance);
            this.distanceLeftUnits = itemView.findViewById(R.id.estimate_distance_units);

            this.nextStop = itemView.findViewById(R.id.next_stop_name);
            this.directionStop = itemView.findViewById(R.id.direction_name);
        }
    }

    /*
        Both next functions are responsible for the dropdown of the detail Layout.

        From https://stackoverflow.com/a/50222157
     */

    private static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);

        ValueAnimator va = ValueAnimator.ofInt(1, targetHeight);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                v.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }

            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });
        va.setDuration(300);
        va.setInterpolator(new OvershootInterpolator());
        va.start();
    }

    private static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        ValueAnimator va = ValueAnimator.ofInt(initialHeight, 0);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
            }

            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });
        va.setDuration(300);
        va.setInterpolator(new DecelerateInterpolator());
        va.start();
    }
}