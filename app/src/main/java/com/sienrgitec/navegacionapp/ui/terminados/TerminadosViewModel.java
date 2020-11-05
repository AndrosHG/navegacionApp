package com.sienrgitec.navegacionapp.ui.terminados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TerminadosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TerminadosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Historial de pedidos terminados");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
