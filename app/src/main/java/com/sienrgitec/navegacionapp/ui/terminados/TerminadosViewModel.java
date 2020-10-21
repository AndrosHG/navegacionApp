package com.sienrgitec.navegacionapp.ui.terminados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TerminadosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TerminadosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is terminados fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
