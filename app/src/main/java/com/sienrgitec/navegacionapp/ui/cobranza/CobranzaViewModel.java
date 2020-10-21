package com.sienrgitec.navegacionapp.ui.cobranza;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CobranzaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CobranzaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Cobranza fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
