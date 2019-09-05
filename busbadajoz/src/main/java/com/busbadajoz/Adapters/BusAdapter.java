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

    private int bus_tapped = -1;
    private int bus_selected;
    private ArrayList<BusModel> buses;
    private ArrayList<BusModel> buses_new;
    private ArrayList<Boolean> bus_state;
    private Context mContext;

    private BusAdapterInterface adapterInterface;

    public BusAdapter(ArrayList<BusModel> buses, ArrayList<BusModel> buses_new, int bus_selected, ArrayList<Boolean> bus_states, Context mContext, BusAdapterInterface adapterInterface) {
        /*
            Ok, if you made it here there is something to explain. There are two arrays in the constructor,
            that will be later checked if equals to set the text. This happens because we are using
            a TickerView to display the time that the bus will take to arrive to get a nice animation
            on updates.

            To get that animation we have to call the setText() method of the TickerView, and I didn't
            find another way to do that apart from the following (there probably is a better way, I'm
            sure, so I'm open to suggestions):

            If there's been an update on the times we'll call setText(), but _only_ if there's an update,
            as we are recreating this recyclerview every time a tap in any bus happens. To control this
            we pass at first the same arrays, so no animations happen.

            Then, as we update the data we call updateData() on the StopAdapter, which recreates the
            stop views with two different arrays to make the animation and then updates the old data
            with the new.
         */

        Log.d(TAG, "BusAdapter: created, at 0,0 there's " + buses_new.get(0).getTimeLeft());
        this.bus_selected = bus_selected;
        this.buses = buses;
        this.buses_new = buses_new;
        this.mContext = mContext;

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

        /*
            We have to do this to get the ticker animation from TickerView, triggered by a setText()
            call that we make when the update the data from the parent recyclerView.
         */
        if (!buses.get(position).getTimeLeft().equals(buses_new.get(position).getTimeLeft())){
            holder.time_left.setAnimationDuration(350);
            holder.time_left.setText(buses.get(position).getTimeLeft());
            holder.time_left.setText(buses_new.get(position).getTimeLeft());
            holder.time_left.setAnimationDuration(0);

            //Once the animation happened, we update the array so it never happens again on the same value
            buses.get(position).setTimeLeft(buses_new.get(position).getTimeLeft());
        } else {
            holder.time_left.setText(buses.get(position).getTimeLeft());
        }

        if (holder.time_left.getText().equals("1")){
            holder.unit_time_left.setText(R.string.units_time_left);
        } else {
            holder.unit_time_left.setText(R.string.units_time_left_plural);
        }

        holder.bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.OnItemClicked(position, bus_state);

                if (bus_selected == position){
                    bus_selected = -1;
                    notifyItemChanged(position);
                } else {
                    bus_tapped = bus_selected;

                    bus_selected = position;
                    notifyItemChanged(position);
                    notifyItemChanged(bus_tapped);
                }
            }
        });

        // Change only the color of the tapped bus.
        if (bus_selected == position) {
            holder.bottom_triangle.setVisibility(View.VISIBLE);
        } else {
            holder.bottom_triangle.setVisibility(View.INVISIBLE);
        }
    }

    private void setBusWarning(BusViewHolder holder){
        ((GradientDrawable) holder.bus.getBackground()).setColor(Color.parseColor("#B00020"));
        holder.line_name.setTextColor(Color.WHITE);
        holder.time_left.setTextColor(Color.WHITE);
        holder.unit_time_left.setTextColor(Color.WHITE);
    }

    private void removeBusWarning(BusViewHolder holder){
        ((GradientDrawable) holder.bus.getBackground()).setColor(Color.TRANSPARENT);
        holder.line_name.setTextColor(Color.BLACK);
        holder.time_left.setTextColor(Color.BLACK);
        holder.unit_time_left.setTextColor(Color.BLACK);
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
        /*
            There's an error in the "color" attribute as some objects don't have it,
            but we will only use it with text and solid backgrounds, so don't worry.
         */
        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(object,
                "color",
                new ArgbEvaluator(),
                colorInit,
                colorFinal);
        backgroundColorAnimator.setDuration(250);
        backgroundColorAnimator.start();
    }
    private void changetextColor(Object object, int colorInit, int colorFinal) {
        /*
            There's an error in the "color" attribute as some objects don't have it,
            but we will only use it with text and solid backgrounds, so don't worry.
         */
        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(object,
                "textColor",
                new ArgbEvaluator(),
                colorInit,
                colorFinal);
        backgroundColorAnimator.setDuration(250);
        backgroundColorAnimator.start();
    }
}
