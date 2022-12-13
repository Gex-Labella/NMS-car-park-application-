package com.lizyaver.lizyaverorg.ui.siblings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SiblingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SiblingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}