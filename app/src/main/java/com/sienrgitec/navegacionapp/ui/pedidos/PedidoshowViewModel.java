package com.sienrgitec.navegacionapp.ui.pedidos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PedidoshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PedidoshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Extras fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}