package com.busbadajoz.Adapters;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busbadajoz.R;

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

    private int row_index = -1;
    private ArrayList<StopModel> stop_models;
    private ArrayList<StopModelState> stop_states = new ArrayList<>();

    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public StopAdapter(ArrayList<StopModel> stop_models, Context mContext) {
        this.stop_models = stop_models;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();

        for (int i = 0; i < this.stop_models.size(); i++ ){
            this.stop_states.add(new StopModelState(this.stop_models.get(i).getAllItemInSection().size()));
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


        BusAdapter.BusAdapterInterface adapterInterface = new BusAdapter.BusAdapterInterface() {
            @Override
            public void OnItemClicked(int item_id, ArrayList<Boolean> buses_states) {
                stop_states.get(position).setBusesStates(buses_states);
                stop_states.get(position).setScrollState(holder.recyclerView.getLayoutManager().onSaveInstanceState());

                if (item_id == stop_states.get(position).getBusSelected()) {
                    stop_states.get(position).setBusSelected(-1);
                } else {
                    stop_states.get(position).setBusSelected(item_id);
                }

                //buses_activ.set(position, buses_states);
                row_index = position;
                notifyItemChanged(position);
            }
        };

        ArrayList singleSectionItems = stop_models.get(position).getAllItemInSection();
        BusAdapter adapter = new BusAdapter(singleSectionItems, stop_states.get(position).getBusesStates(), mContext, adapterInterface);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);

        if (stop_states.get(position).getScrollState() != null) {
            holder.recyclerView.getLayoutManager().onRestoreInstanceState(stop_states.get(position).getScrollState());
        }

        if(row_index == position){
            if (!stop_states.get(position).getActive()){
                holder.prueba_texto.setVisibility(View.VISIBLE);
                stop_states.get(position).setActive(true);
            } else {
                boolean active = false;
                /*
                if (stop_states.get(position).getBusSelected() != -1) {
                    active = true;
                }*/

                for (Boolean bus :  stop_states.get(position).getBusesStates()) {
                    if (bus) {
                        active = true;
                        break;
                    }
                }

                if (!active) {
                    holder.prueba_texto.setVisibility(View.GONE);
                    stop_states.get(position).setActive(false);
                } else {
                    holder.prueba_texto.setVisibility(View.VISIBLE);
                    stop_states.get(position).setActive(true);
                }
            }
            row_index = -1;

        }
        else
        {
            if (stop_states.get(position).getActive()){
                holder.prueba_texto.setVisibility(View.VISIBLE);
            } else {
                holder.prueba_texto.setVisibility(View.GONE);
            }
        }


        if (position == stop_models.size() - 1) {

            /* Bottom padding is not set in the layout, so in the last element we need to add it
                so it's not close to the bottom menu.
             */

            //https://stackoverflow.com/a/4275969
            int padding_in_dp = 12;
            final float scale = mContext.getResources().getDisplayMetrics().density;
            int padding_in_px = (int) (padding_in_dp * scale + 0.5f);

            holder.stop_layout.setPadding(
                    holder.stop_layout.getPaddingLeft(),
                    holder.stop_layout.getPaddingTop(),
                    holder.stop_layout.getPaddingEnd(),
                    padding_in_px);
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
        protected TextView prueba_texto;

        protected Parcelable listState;
        
        protected ConstraintLayout stop_layout;

        public StopViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.recyclerView = itemView.findViewById(R.id.bus_list);
            this.distance = itemView.findViewById(R.id.distance);
            this.prueba_texto = itemView.findViewById(R.id.expand_test);
            this.stop_layout = itemView.findViewById(R.id.stop_layout);
        }
    }

    protected class StopModelState{

        /*
            This is the state of every stop. It includes the state of every of the buses, to change
            the color of its background.

            Also saves if one of them has been selected and its position, to display a descriptive
            text behind the list.

            And lastly, it saves the LayoutManager state to keep the scrolling state in the buses
            recyclerview.
         */

        private ArrayList<Boolean> buses_states = new ArrayList<>();
        private int bus_selected;
        private Parcelable scroll_state;
        private Boolean active;

        private StopModelState(int bus_number){
            this.bus_selected = -1;
            this.scroll_state = null;
            this.active = false;

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

        public Boolean getActive(){
            return this.active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }



    }

}