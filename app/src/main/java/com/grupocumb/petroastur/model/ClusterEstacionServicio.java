package com.grupocumb.petroastur.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterEstacionServicio implements ClusterItem {

    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;
    private final EstacionServicio estacionServicio;

    public ClusterEstacionServicio(LatLng mPosition, String mTitle, String mSnippet, EstacionServicio estacionServicio) {
        this.mPosition = mPosition;
        this.mTitle = mTitle;
        this.mSnippet = mSnippet;
        this.estacionServicio = estacionServicio;
    }


    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public EstacionServicio getEstacionServicio() {
        return estacionServicio;
    }
}
