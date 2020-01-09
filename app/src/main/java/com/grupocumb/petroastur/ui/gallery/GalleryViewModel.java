package com.grupocumb.petroastur.ui.gallery;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<Location> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<Location>();
    }

    public LiveData<Location> getText() {
        return mText;
    }

    public void setmText(Location e) {
        this.mText.setValue(e);
    }
}