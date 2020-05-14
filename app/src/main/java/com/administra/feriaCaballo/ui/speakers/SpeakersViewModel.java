package com.administra.feriaCaballo.ui.speakers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpeakersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpeakersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}