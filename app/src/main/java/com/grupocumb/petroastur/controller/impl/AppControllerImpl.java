package com.grupocumb.petroastur.controller.impl;

import android.content.Context;

import androidx.room.FtsOptions;

import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.controller.DataController;
import com.grupocumb.petroastur.controller.SettingsController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppControllerImpl implements AppController {

    private DataController dataController;
    private SettingsController settingsController;

    public AppControllerImpl(Context context) {
        dataController = new DataControllerImpl(context);
        settingsController = new SettingsControllerImpl(context);
    }

    /**
     *
     */
    private void updateAll() {
        //TODO
        //1. Chequea la ultima fecha de actalizacion en settings
        //2. Si no es la hora que se actalizadba y demas hace un update.
        //   Si esta actualiza cambia el valor a updated a true.
        //3. Una vez actualizado cambiar el estado a actualizado.
    }

    @Override
    public List<EstacionServicio> getAllEESS() {
        return dataController.getAll();
    }

    @Override
    public List<EstacionServicio> getAllEESSOrdered() {
        //TODO VALORES PARA TEST, CAMBIAR !!!!!!!!!!!!!!!!
        OrderType favouriteOrder = OrderType.PRECIO;//settingsController.getFavouriteOrder();
        FuelType favouriteFuel = FuelType.GASOLEO_A;//settingsController.getFavouriteFuel();

        if(favouriteOrder == OrderType.PRECIO) {
            return dataController.getAll().stream()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel)))
                    .collect(Collectors.toList());
        }
        else {
            // TODO ORDEN POR DISTANCIA
            return null;
        }
    }

    @Override
    public List<EstacionServicio> getFavouritesOrdered() {
        return null;
    }

    @Override
    public void addFavourite(int id) {

    }

    @Override
    public void removeFavourite(int id) {
        
    }

    @Override
    public List<EstacionServicio> getEESSByIds(int[] id) {
        return null;
    }

    @Override
    public FuelType getSettingFavouriteFuel() {
        return null;
    }

    @Override
    public void setSettingFavouriteFuel(FuelType fuelType) {

    }

    @Override
    public OrderType getSettingOrder() {
        return null;
    }

    @Override
    public void setSettingOrder(OrderType orderType) {

    }

    @Override
    public double getSettingMaxDistance() {
        return 0;
    }

    @Override
    public void setSettingMaxDistance(double settingMaxDistance) {

    }
}
