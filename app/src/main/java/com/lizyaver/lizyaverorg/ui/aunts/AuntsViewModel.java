package com.lizyaver.lizyaverorg.ui.aunts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuntsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AuntsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}