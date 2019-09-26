package com.busbadajoz.Adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModel;
import com.busbadajoz.models.BusModelView;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder>{

    private String TAG = "BUSAdapter";

    private int bus_tapped = -1;
    private int bus_selected;
    private int bus_size;
    private LiveData<ArrayList<BusModelView>> buses;
    private MutableLiveData<BusModel> bus_live;

    private LifecycleOwner lifecycleOwner;
    //private ArrayList<BusModel> buses_new;
    private ArrayList<Boolean> bus_state;
    private Context mContext;

    private BusAdapterInterface adapterInterface;

    public BusAdapter(LiveData<ArrayList<BusModelView>> buses, int bus_selected,
                      ArrayList<Boolean> bus_states, int bus_size, Context mContext, BusAdapterInterface adapterInterface,
                      LifecycleOwner lifecycleOwner) {
        /*
            On this adapter we get a LiveData of all the data necessary to display. At first, the
            time left will be -1, as we don't have the parsed data yet. When this happens, we will
            show a view containing a loading animation (or text, I don't know yet but just a
            placeholder).

            When we get the data, it will be shown and updated with the TickerView animation.
         */

        //Log.d(TAG, "BusAdapter: created, at 0,0 there's " + buses_new.get(0).getTimeLeft());
        this.bus_selected = bus_selected;
        this.buses = buses;
        this.lifecycleOwner = lifecycleOwner;
        //this.buses_new = buses_new;
        this.bus_size = bus_size;
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
    public void onBindViewHolder(final BusViewHolder holder, final int position) {

        //It looks like we can't set a font from xml so we ned this
        //From https://github.com/robinhood/ticker/issues/92#issuecomment-503526527
        Typeface fontFace = ResourcesCompat.getFont(mContext, R.font.lato_black);
        holder.time_left.setTypeface(fontFace);

        buses.observe(this.lifecycleOwner, new Observer<ArrayList<BusModelView>>() {
            @Override
            public void onChanged(ArrayList<BusModelView> busesData) {
                if (busesData.get(position).getTimeLeft() != -1) {
                    holder.loadingView.setVisibility(View.GONE);
                    holder.bus.setVisibility(View.VISIBLE);
                    holder.showingData = true;
                }

                //All the data will change, so let's check on every bus and only change the text if
                //its data has changed.
                if (!holder.time_left.getText().equals(busesData.get(position).getTimeLeft())) {

                    // At first, we don't need the animation
                    if (!holder.time_left.getText().equals("-1") && !holder.time_left.getText().equals("-")){
                        holder.time_left.setAnimationDuration(350);
                    } else {
                        holder.time_left.setAnimationDuration(0);
                    }

                    holder.time_left.setText(String.valueOf(busesData.get(position).getTimeLeft()));
                    holder.line_name.setText(busesData.get(position).getLineName());

                    if (holder.time_left.getText().equals("1")){
                        holder.unit_time_left.setText(R.string.units_time_left);
                    } else {
                        holder.unit_time_left.setText(R.string.units_time_left_plural);
                    }
                }
            }
        });

        /*
            Show the detailed info when it is touched. Only when the info is finally shown, as the
            buses order will change depending on their availability (some lines are only run at
            night, or on certain days, for example, and the parsing will make sure the active lines
            at the time are shown first).

            On the first load, to avoid a sudden change on that order, we won't show the lines info
            immediately, but when the data is already processed and passed here. It will be then
            when the user will be able to see the detailed info.

            This behaviour *should* be temporal. Ideally the detailed info should be available at all
            times, as it doesn't depend on the time left to the bus to arrive, but right now I don't
            know how to implement it without getting ugly, so it is a compromise we have to make.
         */
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

    private void setBusWarning(BusViewHolder holder) {
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
        return (null != buses.getValue() ? buses.getValue().size() : this.bus_size);
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        private Boolean showingData;

        private TextView line_name;
        private TickerView time_left;
        private TextView unit_time_left;

        private ConstraintLayout bottom_triangle;
        private ConstraintLayout bus;
        private ConstraintLayout loadingView;
        private ConstraintLayout fullView;

        private Observer<ArrayList<BusModelView>> dataObserver;

        public BusViewHolder(View itemView) {
            super(itemView);

            this.showingData = false;

            this.line_name = itemView.findViewById(R.id.line_name);
            this.time_left = itemView.findViewById(R.id.time_left);
            this.unit_time_left = itemView.findViewById(R.id.units_time_left);

            this.bus = itemView.findViewById(R.id.bus_border);
            this.loadingView = itemView.findViewById(R.id.loading_view);
            this.fullView = itemView.findViewById(R.id.full_bus_layout);
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
