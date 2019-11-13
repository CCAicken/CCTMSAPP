package com.example.cctms.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class homeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public homeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("主页");
    }

    public LiveData<String> getText() {
        return mText;
    }
}