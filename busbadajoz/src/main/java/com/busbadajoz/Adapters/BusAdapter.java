package com.busbadajoz.Adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModelView;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder>{

    private String TAG = "BUSAdapter";

    private int bus_tapped = -1;
    private int bus_selected;
    private int bus_size;
    private ArrayList<BusModelView> buses;
    private ArrayList<Boolean> bus_state;
    private Context mContext;

    private BusAdapterInterface adapterInterface;

    public BusAdapter(ArrayList<BusModelView> buses, int bus_selected,
                      ArrayList<Boolean> bus_states, int bus_size, Context mContext,
                      BusAdapterInterface adapterInterface) {
        this.bus_selected = bus_selected;


        this.buses = buses;
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
        holder.timeLeft.setTypeface(fontFace);

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

        holder.lineName.setText(this.buses.get(position).getLineName());
        holder.timeLeft.setText(String.valueOf(this.buses.get(position).getTimeLeft()));
        holder.unitsTimeLeft.setText(this.buses.get(position).getUnitTimeLeft());

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
            holder.bottomTriangle.setVisibility(View.VISIBLE);
        } else {
            holder.bottomTriangle.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(final BusViewHolder holder, final int position, List<Object> payloads) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {

                if (key.equals("NAME")) {
                    //Update the time units
                    holder.lineName.setText((String) o.get(key));
                }

                if (key.equals("TIME")) {
                    //Update the time
                    holder.timeLeft.setAnimationDuration(250);
                    holder.timeLeft.setText(String.valueOf( (Integer) o.get(key)));
                    holder.timeLeft.setAnimationDuration(0);
                }

                if (key.equals("TIME_UNITS")) {
                    //Update the time units
                    holder.unitsTimeLeft.setText((String) o.get(key));
                }
            }

            if (!holder.timeLeft.getText().equals("-1")) {
                holder.loadingView.setVisibility(View.GONE);
                holder.bus.setVisibility(View.VISIBLE);
                holder.showingData = true;
            }
        }
    }


    private void setBusWarning(BusViewHolder holder) {
        ((GradientDrawable) holder.bus.getBackground()).setColor(Color.parseColor("#B00020"));
        holder.lineName.setTextColor(Color.WHITE);
        holder.timeLeft.setTextColor(Color.WHITE);
        holder.unitsTimeLeft.setTextColor(Color.WHITE);
    }

    private void removeBusWarning(BusViewHolder holder){
        ((GradientDrawable) holder.bus.getBackground()).setColor(Color.TRANSPARENT);
        holder.lineName.setTextColor(Color.BLACK);
        holder.timeLeft.setTextColor(Color.BLACK);
        holder.unitsTimeLeft.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return (null != buses ? buses.size() : this.bus_size);
    }

    public void updateData(ArrayList<BusModelView> newData){
        Log.d(TAG, "updateData: new Data = " + newData);
        Log.d(TAG, "updateData: old Data = " + this.buses);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new BusDiffUtilCallback(this.buses, newData));
        diffResult.dispatchUpdatesTo(this);

        Log.d(TAG, "updateData (Bus): Finished update");
        this.buses = newData;
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        private Boolean showingData;

        private TextView lineName;
        private TickerView timeLeft;
        private TextView unitsTimeLeft;

        private ConstraintLayout bottomTriangle;
        private ConstraintLayout bus;
        private ConstraintLayout loadingView;
        private ConstraintLayout fullView;

        private Observer<ArrayList<BusModelView>> dataObserver;

        public BusViewHolder(View itemView) {
            super(itemView);

            this.showingData = false;

            this.lineName = itemView.findViewById(R.id.line_name);
            this.timeLeft = itemView.findViewById(R.id.time_left);
            this.unitsTimeLeft = itemView.findViewById(R.id.units_time_left);

            this.bus = itemView.findViewById(R.id.bus_border);
            this.loadingView = itemView.findViewById(R.id.loading_view);
            this.fullView = itemView.findViewById(R.id.full_bus_layout);
            this.bottomTriangle = itemView.findViewById(R.id.lower_triangle_layout);
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
