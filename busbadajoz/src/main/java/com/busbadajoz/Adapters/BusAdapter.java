package com.busbadajoz.Adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder>{

    private String TAG = "BUSAdapter";

    private int bus_selected = -1;
    private ArrayList<BusModel> buses;
    private ArrayList<Boolean> bus_state;
    private Context mContext;

    private BusAdapterInterface adapterInterface;

    public BusAdapter(ArrayList<BusModel> buses, ArrayList<Boolean> bus_states, Context mContext, BusAdapterInterface adapterInterface) {
        this.buses = buses;
        this.mContext = mContext;

        //this.bus_selected = selected;

        this.bus_state = bus_states;

        this.adapterInterface = adapterInterface;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_layout, parent, false);
        BusViewHolder singleItemRowHolder = new BusViewHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, final int position) {

        holder.line_name.setText(buses.get(position).getLine());

        //holder.time_left.setText(buses.get(position).getTimeLeft());

        holder.unit_time_left.setText("min");

        holder.bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus_selected = position;

                for (int i = 0; i < bus_state.size(); i++){
                    if (bus_state.get(i) && i != position) {
                        bus_state.set(i,false);
                        notifyItemChanged(i);
                        break;
                    }
                }
                notifyItemChanged(position);
            }
        });


        // Change only the color of the tapped bus.
        if (bus_selected == position) {
            if (!bus_state.get(position)){
                changeColor((GradientDrawable) holder.bus.getBackground(), Color.TRANSPARENT,Color.parseColor("#B00020"));

                changeColor(holder.line_name, Color.BLACK, Color.WHITE);
                changeColor(holder.time_left, Color.BLACK, Color.WHITE);
                changeColor(holder.unit_time_left, Color.BLACK, Color.WHITE);
                holder.bottom_triangle.setVisibility(View.VISIBLE);

                bus_state.set(position, true);

            } else {
                changeColor((GradientDrawable) holder.bus.getBackground(), Color.parseColor("#B00020"), Color.TRANSPARENT);

                changeColor(holder.line_name, Color.WHITE, Color.BLACK);
                changeColor(holder.time_left, Color.WHITE, Color.BLACK);
                changeColor(holder.unit_time_left, Color.WHITE, Color.BLACK);

                bus_state.set(position, false);
                holder.bottom_triangle.setVisibility(View.INVISIBLE);
            }
            bus_selected = -1;

            adapterInterface.OnItemClicked(position, bus_state);

        } else {
            if (bus_state.get(position)){
                ((GradientDrawable) holder.bus.getBackground()).setColor(Color.parseColor("#B00020"));
                holder.line_name.setTextColor(Color.WHITE);
                holder.time_left.setTextColor(Color.WHITE);
                holder.unit_time_left.setTextColor(Color.WHITE);
                holder.bottom_triangle.setVisibility(View.VISIBLE);
            } else {
                ((GradientDrawable) holder.bus.getBackground()).setColor(Color.TRANSPARENT);
                holder.line_name.setTextColor(Color.BLACK);
                holder.time_left.setTextColor(Color.BLACK);
                holder.unit_time_left.setTextColor(Color.BLACK);
                holder.bottom_triangle.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: BUSAdapter called" + buses.size() );

        return (null != buses ? buses.size() : 0);
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        private TextView line_name;
        private TickerView time_left;
        private TextView unit_time_left;
        private ConstraintLayout bottom_triangle;

        private ConstraintLayout bus;

        public BusViewHolder(View itemView) {
            super(itemView);
            this.line_name = itemView.findViewById(R.id.line_name);
            this.time_left = itemView.findViewById(R.id.time_left);
            this.unit_time_left = itemView.findViewById(R.id.units_time_left);
            this.bus = itemView.findViewById(R.id.bus_border);
            this.bottom_triangle = itemView.findViewById(R.id.lower_triangle_layout);
        }

    }

    /* An interface to send data back the stop item. It sends back the position of the item tapped
       and the state of all of the buses displayed.
     */
    public interface BusAdapterInterface{
        void OnItemClicked(int item_id, ArrayList<Boolean> bus_states);
    }

    /*
        Function to change the color of something. It is used to set a bus layout background to red.
     */
    private void changeColor(Object object, int colorInit, int colorFinal) {
        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(object,
                "color",
                new ArgbEvaluator(),
                colorInit,
                colorFinal);
        backgroundColorAnimator.setDuration(250);
        backgroundColorAnimator.start();
    }
}
