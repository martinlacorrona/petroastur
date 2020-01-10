package com.grupocumb.petroastur.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Set;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<Set<String>> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<Set<String>> getText() {
        return mText;
    }

    public void setmText(Set<String> e) {
        this.mText.setValue(e);
    }
}