package com.sienrgitec.navegacionapp.ui.problemas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProblemaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProblemaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Cobranza fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
