package com.grupocumb.petroastur.controller;

import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;

import java.util.Date;
import java.util.List;

public interface SettingsController {
    Date getLastUpddate();

    void setLastUpdate(Date date);

    List<EstacionServicio> getFavourites();

    void setFavourites(List<EstacionServicio> favourites);

    FuelType getFavouriteFuel();

    void setFavouriteFuel(FuelType fuel);

    OrderType getFavouriteOrder();

    void setFavouriteOrder(OrderType order);

    double getMaxDistance();

    void setMaxDistance(double distance);
}
