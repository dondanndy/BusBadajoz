package com.busbadajoz.Adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busbadajoz.R;
import com.busbadajoz.models.BusModelView;
import com.google.android.material.card.MaterialCardView;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder>{

    private String TAG = "BUSAdapter";

    private int bus_tapped = -1;
    private int bus_selected;
    private int bus_size;
    private ArrayList<BusModelView> buses;
    private Context mContext;

    private int distThreshold;

    private BusAdapterInterface adapterInterface;

    public BusAdapter(ArrayList<BusModelView> buses, int bus_selected,
                      int bus_size, Context mContext, BusAdapterInterface adapterInterface) {
        this.bus_selected = bus_selected;

        //TODO: Set from prefences.
        this.distThreshold = 700;

        this.buses = buses;
        this.bus_size = bus_size;
        this.mContext = mContext;

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

        //Remove the placeholder if the data is available.
        if (!holder.timeLeft.getText().equals("-1")) {
            holder.loadingView.setVisibility(View.GONE);
            holder.bus.setVisibility(View.VISIBLE);
            holder.showingData = true;
        }

        holder.bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bus_selected == position){
                    bus_selected = -1;
                    notifyItemChanged(position, false);
                } else {
                    notifyItemChanged(bus_selected, false);
                    notifyItemChanged(position, true);

                    bus_selected = position;
                }

                adapterInterface.OnItemClicked(position, bus_selected);
            }
        });

        //Change color for warning
        if (buses.get(position).getUnitDistanceLeft().equals("metros")){
            //Our locale sets a coma as the decimal separator, but we can't parse with it.
            if (Float.parseFloat(buses.get(position).getDistanceLeft().replace(',', '.')) < distThreshold) {
                holder.materialCard.setCardBackgroundColor(Color.parseColor("#B00020"));

                holder.lineName.setTextColor(Color.WHITE);
                holder.timeLeft.setTextColor(Color.WHITE);
                holder.unitsTimeLeft.setTextColor(Color.WHITE);
            }
        }

        // Change only the color of the tapped bus.
        if (bus_selected == position) {
            holder.bottomTriangle.setVisibility(View.VISIBLE);
        } else {
            holder.bottomTriangle.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position);
        } else if (payloads.get(0) instanceof Boolean){
            if ((Boolean) payloads.get(0)){
                holder.bottomTriangle.setVisibility(View.VISIBLE);
                holder.materialCard.animate()
                        .translationZ(holder.materialCard.getZ() * 4)
                        .setDuration(350)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .start();
            } else {
                holder.bottomTriangle.setVisibility(View.INVISIBLE);
                holder.materialCard.animate()
                        .translationZ(0)
                        .setDuration(350)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .start();
            }
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {

                if (key.equals("NAME")) {
                    //Update the time units
                    holder.lineName.setText((String) o.get(key));
                }

                if (key.equals("TIME")) {
                    //Update the time
                    if (!holder.timeLeft.getText().equals("-1")){
                        holder.timeLeft.setAnimationDuration(400);
                    }
                    holder.timeLeft.setText(String.valueOf( (Integer) o.get(key)));
                    holder.timeLeft.setAnimationDuration(0);
                }

                if (key.equals("TIME_UNITS")) {
                    //Update the time units
                    if (!holder.timeLeft.getText().equals("-1")){
                        holder.unitsTimeLeft.setAnimationDuration(400);
                    }
                    holder.unitsTimeLeft.setText((String) o.get(key));
                    holder.unitsTimeLeft.setAnimationDuration(0);
                }

                if (key.equals("DISTANCE")) {
                    //Change warning colors if distance is low enough
                    if (((String) o.get("DISTANCE_UNITS")).equals("metros")){
                        //Our locale sets a coma as the decimal separator, but we can't parse with it.
                        if (Float.parseFloat(((String) o.get(key)).replace(',', '.')) < distThreshold) {
                            changeColor(holder.materialCard, Color.WHITE, Color.parseColor("#B00020"));

                            changetextColor(holder.lineName, Color.BLACK, Color.WHITE);
                            changetextColor(holder.timeLeft, Color.BLACK, Color.WHITE);
                            changetextColor(holder.unitsTimeLeft, Color.BLACK, Color.WHITE);
                        }
                    } else {
                        changeColor(holder.materialCard, Color.parseColor("#B00020"), Color.WHITE);

                        changetextColor(holder.lineName, Color.WHITE, Color.BLACK);
                        changetextColor(holder.timeLeft, Color.WHITE, Color.BLACK);
                        changetextColor(holder.unitsTimeLeft, Color.WHITE, Color.BLACK);
                    }
                }
            }

            //Remove the placeholder if the data is available.
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
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new BusDiffUtilCallback(this.buses, newData));
        diffResult.dispatchUpdatesTo(this);

        this.buses = newData;
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        private Boolean showingData;

        private TextView lineName;
        private TickerView timeLeft;
        private TickerView unitsTimeLeft;

        private ConstraintLayout bottomTriangle;
        private MaterialCardView materialCard;

        private LinearLayout bus;
        private ConstraintLayout loadingView;
        private LinearLayout fullView;

        private Observer<ArrayList<BusModelView>> dataObserver;

        public BusViewHolder(View itemView) {
            super(itemView);

            this.showingData = false;

            this.lineName = itemView.findViewById(R.id.line_name);
            this.timeLeft = itemView.findViewById(R.id.time_left);
            this.unitsTimeLeft = itemView.findViewById(R.id.units_time_left);

            this.bus = itemView.findViewById(R.id.bus_layout);
            this.loadingView = itemView.findViewById(R.id.loading_view);
            this.fullView = itemView.findViewById(R.id.full_bus_layout);
            this.bottomTriangle = itemView.findViewById(R.id.lower_triangle_layout);
            this.materialCard = itemView.findViewById(R.id.bus_border);
        }

    }

    /* An interface to send data back the stop item. It sends back the position of the item tapped
       and the state of all of the buses displayed.
     */
    public interface BusAdapterInterface{
        void OnItemClicked(int item_id, int selected);
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
                "cardBackgroundColor",
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
