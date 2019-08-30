package com.busbadajoz.Adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_layout, null);
        BusViewHolder singleItemRowHolder = new BusViewHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, final int position) {
        holder.line_name.setText(buses.get(position).getLine());
        holder.time_left.setText(buses.get(position).getTimeLeft());
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
                final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject((GradientDrawable) holder.bus.getBackground(),
                        "color",
                        new ArgbEvaluator(),
                        Color.TRANSPARENT,
                        Color.parseColor("#B00020"));
                backgroundColorAnimator.setDuration(250);
                backgroundColorAnimator.start();

            final ObjectAnimator textColorAnimator = ObjectAnimator.ofObject(holder.time_left,
                    "TextColor",
                    new ArgbEvaluator(),
                    Color.BLACK,
                    Color.WHITE);
            textColorAnimator.setDuration(250);
            textColorAnimator.start();

            holder.bottom_triangle.setVisibility(View.VISIBLE);


            bus_state.set(position, true);

            } else {
                final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject((GradientDrawable) holder.bus.getBackground(),
                        "color",
                        new ArgbEvaluator(),
                        Color.parseColor("#B00020"),
                        Color.TRANSPARENT);
                backgroundColorAnimator.setDuration(250);
                backgroundColorAnimator.start();

                final ObjectAnimator textColorAnimator = ObjectAnimator.ofObject(holder.time_left,
                        "TextColor",
                        new ArgbEvaluator(),
                        Color.WHITE,
                        Color.BLACK);
                textColorAnimator.setDuration(250);
                textColorAnimator.start();

                bus_state.set(position, false);
                holder.bottom_triangle.setVisibility(View.INVISIBLE);
            }
            bus_selected = -1;

            adapterInterface.OnItemClicked(position, bus_state);

        } else {
            if (bus_state.get(position)){
                ((GradientDrawable) holder.bus.getBackground()).setColor(Color.parseColor("#B00020"));
                holder.time_left.setTextColor(Color.WHITE);
                holder.bottom_triangle.setVisibility(View.VISIBLE);
            } else {
                ((GradientDrawable) holder.bus.getBackground()).setColor(Color.TRANSPARENT);
                holder.time_left.setTextColor(Color.BLACK);
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

        protected TextView line_name;
        protected TextView time_left;
        protected TextView unit_time_left;
        protected ConstraintLayout bottom_triangle;

        protected ConstraintLayout bus;

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

}
