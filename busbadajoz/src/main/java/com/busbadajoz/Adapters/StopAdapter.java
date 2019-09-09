package com.busbadajoz.Adapters;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.StopModel;

import java.util.ArrayList;

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder>{

    /*
        Here we need to save the state of every stop of the list to restore them when scrolling
        as the adapter reuses the view set before ignoring the previous state.

        For that we are going to keep a list of a state structure, restoring the stop state to
        the one
     */

    final private String TAG = "StopAdapter";

    private ArrayList<Boolean> updated = new ArrayList<>();

    private int row_index = -1;
    private ArrayList<StopModel> stop_models;
    private ArrayList<StopModel> stop_models_new;
    private ArrayList<StopModelState> stop_states = new ArrayList<>();

    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public StopAdapter(ArrayList<StopModel> stop_models, Context mContext) {
        this.stop_models = stop_models;
        this.stop_models_new = stop_models;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();

        for (int i = 0; i < this.stop_models.size(); i++ ){
            this.stop_states.add(new StopModelState(this.stop_models.get(i).getAllItemInSection().size()));
            this.updated.add(false);
        }
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_layout, parent, false);
        StopViewHolder rowHolder = new StopViewHolder(v);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(final StopViewHolder holder, final int position) {

        holder.name.setText(stop_models.get(position).getName());
        holder.distance.setText("13 km");

        /*
         ----------- Buses RecyclerView Setup -----------------------------------------------
         */

        //Interface for bus tap notification.
        BusAdapter.BusAdapterInterface adapterInterface = new BusAdapter.BusAdapterInterface() {
            @Override
            public void OnItemClicked(int item_id, ArrayList<Boolean> buses_states) {
                stop_states.get(position).setBusesStates(buses_states);
                stop_states.get(position).setScrollState(holder.recyclerView.getLayoutManager().onSaveInstanceState());

                row_index = position;
                if (item_id == stop_states.get(position).getBusSelected()){
                    collapse(holder.detail);
                    stop_states.get(position).setBusSelected(-1);
                    stop_states.get(position).setShowDetail(false);
                } else {
                    if(stop_states.get(position).getBusSelected() == -1) {
                        expand(holder.detail);
                        stop_states.get(position).setShowDetail(true);
                    }
                    stop_states.get(position).setBusSelected(item_id);
                }
            }
        };

        //Initial data
        ArrayList<BusModel> singleSectionItems = stop_models.get(position).getAllItemInSection();
        ArrayList<BusModel> singleSectionItems_new = stop_models_new.get(position).getAllItemInSection();
        BusAdapter adapter = new BusAdapter(singleSectionItems, singleSectionItems_new, stop_states.get(position).getBusSelected(),
                stop_states.get(position).getBusesStates(), mContext, adapterInterface);

        //Layout and Adapter setup
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        if (this.updated.get(position)){
            stop_models.set(position, stop_models_new.get(position));
            this.updated.set(position, false);
        }

        //Scroll state listener to save the view when the user stops scrolling.
        RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    stop_states.get(position).setScrollState(holder.recyclerView.getLayoutManager().onSaveInstanceState());
                }
            }
        };

        holder.recyclerView.addOnScrollListener(mListener);

        //Restore of the scroll state when the view is recreated.
        if (stop_states.get(position).getScrollState() != null) {
            holder.recyclerView.getLayoutManager().onRestoreInstanceState(stop_states.get(position).getScrollState());
        }

        if (stop_states.get(position).getShowDetail()){
            holder.detail.setVisibility(View.VISIBLE);
        } else {
            holder.detail.setVisibility(View.GONE);
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
        return (null != stop_models ? stop_models.size() : 0);
    }

    public class StopViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView distance;
        protected RecyclerView recyclerView;
        protected LinearLayout detail;
        
        protected ConstraintLayout stop_layout;

        public StopViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.recyclerView = itemView.findViewById(R.id.bus_list);
            this.distance = itemView.findViewById(R.id.distance);
            this.detail = itemView.findViewById(R.id.expand_test);
            this.stop_layout = itemView.findViewById(R.id.stop_layout);
        }
    }

    public void updateData(ArrayList<StopModel> buses_new, int position){
        /*
            This is a callback to update the data from the worker thread when the petition has been
            made and processed. More info in the child adapter constructor.
         */


        Log.d(TAG, "updateData: called with position " + position);

        this.stop_models_new = buses_new;
        for (boolean tmp : this.updated){
            tmp = true;
        }
        notifyDataSetChanged();
    }

    protected class StopModelState{

        /*
            This is the state of every stop. It includes the state of every of the buses, to change
            the color of its background.

            Also saves if one of them has been selected and its position, to display a descriptive
            text below the list.

            And lastly, it saves the LayoutManager state to keep the scrolling state in the buses
            recyclerview.
         */

        private ArrayList<Boolean> buses_states = new ArrayList<>();
        private int bus_selected;
        private Parcelable scroll_state;
        private Boolean showDetail;

        private StopModelState(int bus_number){
            this.bus_selected = -1;
            this.scroll_state = null;
            this.showDetail = false;

            for (int i=0; i < bus_number; i++){
                this.buses_states.add(false);
            }
        }

        public void setBusesStates(ArrayList<Boolean> states){
            this.buses_states = states;
        }

        public ArrayList<Boolean> getBusesStates(){
            return this.buses_states;
        }

        public int getBusSelected() {
            return bus_selected;
        }

        public void setBusSelected(int position) {
            this.bus_selected = position;
        }

        public Parcelable getScrollState() {
            return this.scroll_state;
        }

        public void setScrollState(Parcelable state) {
            this.scroll_state = state;
        }

        public Boolean getShowDetail(){
            return this.showDetail;
        }

        public void setShowDetail(Boolean show_detail) {
            this.showDetail = show_detail;
        }

    }

    /*
        Both next functions are responsible for the dropdown of the detail Layout.

        From https://stackoverflow.com/a/50222157
     */

    public static void expand(final View v) {
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

    public static void collapse(final View v) {
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