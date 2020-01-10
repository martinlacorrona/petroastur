package com.grupocumb.petroastur.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<EstacionServicio>> mText = new MutableLiveData<>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<List<EstacionServicio>> getText() {
        return mText;
    }

    public void setList(List<EstacionServicio> e) {
        this.mText.setValue(e);
    }
}