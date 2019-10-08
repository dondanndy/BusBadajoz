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
        return (this.newList.get(newItemPosition).getTimeLeft() == this.oldList.get(oldItemPosition).getTimeLeft() &&
                this.newList.get(newItemPosition).getUnitTimeLeft().equals(this.oldList.get(oldItemPosition).getUnitTimeLeft()));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {


        BusModelView newValue = this.newList.get(newItemPosition);
        BusModelView oldValue = this.oldList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();

        if (newValue != oldValue) {
            diffBundle.putString("NAME", newValue.getLineName());
            diffBundle.putInt("TIME", newValue.getTimeLeft());
            diffBundle.putString("TIME_UNITS", newValue.getUnitTimeLeft());

            diffBundle.putString("DISTANCE", newValue.getDistanceLeft());
            diffBundle.putString("DISTANCE_UNITS", newValue.getUnitDistanceLeft());
        }
        return diffBundle;
    }
}
