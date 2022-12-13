package com.lizyaver.lizyaverorg.ui.cousins;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CousinsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CousinsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}