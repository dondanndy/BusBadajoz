package com.busbadajoz.Adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.busbadajoz.models.BusModelView;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BusDiffUtilCallback extends DiffUtil.Callback {
    private List<BusModelView> oldList;
    private List<BusModelView> newList;

    public BusDiffUtilCallback (List<BusModelView> oldList, List<BusModelView> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return this.oldList != null ? this.oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return this.newList != null ? this.newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return this.newList.get(newItemPosition).getLineName().equals(this.oldList.get(oldItemPosition).getLineName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return (this.newList.get(newItemPosition).getTimeLeft().equals(this.oldList.get(oldItemPosition).getTimeLeft()));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {


        BusModelView newValue = this.newList.get(newItemPosition);
        BusModelView oldValue = this.oldList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();

        if (newValue != oldValue) {
            //TODO: Find a way to put the pair on the bundle.
            diffBundle.putString("NAME", newValue.getLineName());
            diffBundle.putInt("TIME", newValue.getTimeLeft().first);
            diffBundle.putSerializable("TIME_UNITS", newValue.getTimeLeft().second);

            diffBundle.putString("DISTANCE", newValue.getDistanceLeft().first);
            diffBundle.putSerializable("DISTANCE_UNITS", newValue.getDistanceLeft().second);
        }
        return diffBundle;
    }
}
