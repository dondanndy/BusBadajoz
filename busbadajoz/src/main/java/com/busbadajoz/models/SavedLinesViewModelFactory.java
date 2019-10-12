package com.busbadajoz.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SavedLinesViewModelFactory implements ViewModelProvider.Factory {

    private int size;

    public SavedLinesViewModelFactory(int size) {
        this.size = size;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SavedLinesViewModel(this.size);
    }
}