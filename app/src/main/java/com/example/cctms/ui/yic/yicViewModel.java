package com.example.cctms.ui.yic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class yicViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public yicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("异常记录");
    }

    public LiveData<String> getText() {
        return mText;
    }
}