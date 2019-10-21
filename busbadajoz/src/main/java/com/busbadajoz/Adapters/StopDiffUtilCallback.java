package com.busbadajoz.Adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.busbadajoz.models.BusModelView;
import com.busbadajoz.models.StopModelView;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StopDiffUtilCallback extends DiffUtil.Callback {
    private List<StopModelView> oldList;
    private List<StopModelView> newList;

    public StopDiffUtilCallback (List<StopModelView> oldList, List<StopModelView> newList) {
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
        return this.newList.get(newItemPosition).getName().equals(this.oldList.get(oldItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        //Let's check first for the distance and time left for updating.
        if (this.newList.get(newItemPosition).getDistance() != this.oldList.get(oldItemPosition).getDistance()) {
            return false;
        }

        if (this.newList.get(newItemPosition).getUpdateTime() != this.oldList.get(oldItemPosition).getUpdateTime()) {
            return false;
        }

        //If those are the same, let's check the buses.
        for (int i=0; i < this.oldList.get(oldItemPosition).getBuses().size(); i++) {
            if (!this.newList.get(newItemPosition).getBuses().get(i).getLineName().equals(this.oldList.get(oldItemPosition).getBuses().get(i).getLineName())){
                return false;
            }

            if (this.newList.get(newItemPosition).getBuses().get(i).getTimeLeft() != this.oldList.get(oldItemPosition).getBuses().get(i).getTimeLeft()){
                return false;
            }
        }

        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        StopModelView newValue = this.newList.get(newItemPosition);
        StopModelView oldValue = this.oldList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();

        if (newValue != oldValue) {
            diffBundle.putInt("DISTANCE", newValue.getDistance());

            diffBundle.putInt("UPDATE_TIME", newValue.getUpdateTime().first);
            diffBundle.putSerializable("UPDATE_UNITS", newValue.getUpdateTime().second);

            diffBundle.putParcelableArrayList("BUSES", newValue.getBuses());
        }
        return diffBundle;
    }
}
