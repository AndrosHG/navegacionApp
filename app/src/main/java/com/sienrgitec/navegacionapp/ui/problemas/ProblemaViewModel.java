package com.sienrgitec.navegacionapp.ui.problemas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProblemaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProblemaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Debes ser responsable al momento de reportar un problema. Todo abuso será sancionado");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
