package com.grupocumb.petroastur.controller.impl;

import android.content.Context;

import com.grupocumb.petroastur.controller.SettingsController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;
import com.grupocumb.petroastur.service.SettingsService;
import com.grupocumb.petroastur.service.impl.SettingsServiceImpl;

import java.util.Date;
import java.util.List;

public class SettingsControllerImpl implements SettingsController {

    private SettingsService settingsService;

    public SettingsControllerImpl(Context context) {
        this.settingsService = new SettingsServiceImpl(context);
    }

    @Override
    public Date getLastUpddate() {
        return null;
    }

    @Override
    public void setLastUpdate(Date date) {

    }

    @Override
    public List<EstacionServicio> getFavourites() {
        return null;
    }

    @Override
    public void setFavourites(List<EstacionServicio> favourites) {

    }

    @Override
    public FuelType getFavouriteFuel() {
        return null;
    }

    @Override
    public void setFavouriteFuel(FuelType fuel) {

    }

    @Override
    public OrderType getFavouriteOrder() {
        return null;
    }

    @Override
    public void setFavouriteOrder(OrderType order) {

    }

    @Override
    public double getMaxDistance() {
        return 0;
    }

    @Override
    public void setMaxDistance(double distance) {

    }
}
