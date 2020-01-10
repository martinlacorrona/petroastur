package com.grupocumb.petroastur.ui.detallada;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupocumb.petroastur.model.EstacionServicio;

public class DetalladaViewModel extends ViewModel {

    private MutableLiveData<EstacionServicio> mText;

    public DetalladaViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<EstacionServicio> getText() {
        return mText;
    }

    public void setmText(EstacionServicio e) {
        this.mText.setValue(e);
    }
}