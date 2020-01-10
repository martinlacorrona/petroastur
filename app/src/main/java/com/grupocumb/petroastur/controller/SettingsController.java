package com.grupocumb.petroastur.controller;

import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;

import java.util.Date;

public interface SettingsController {
    Date getLastUpddate();

    void setLastUpdate(Date date);

    String[] getFavourites();

    void setFavourites(String[] favourites);

    FuelType getFavouriteFuel();

    void setFavouriteFuel(FuelType fuel);

    OrderType getFavouriteOrder();

    void setFavouriteOrder(OrderType order);

    double getMaxDistance();

    void setMaxDistance(double distance);
}
