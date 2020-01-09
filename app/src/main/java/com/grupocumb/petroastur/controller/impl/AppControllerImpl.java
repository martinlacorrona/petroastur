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
        OrderType favouriteOrder = OrderType.PRECIO;//settingsController.getFavouriteOrder();
        FuelType favouriteFuel = FuelType.GASOLEO_A;//settingsController.getFavouriteFuel();

        if(favouriteOrder == OrderType.PRECIO) {
            return dataController.getAll().stream()
                    .filter(estacionServicio -> estacionServicio.getPrecioGasolina98Double()>0.0)
                    .sorted(Comparator.comparingDouble(
                    EstacionServicio::getPrecioGasolina98Double)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<EstacionServicio> getFavouritesOrdered() {
        return null;
    }

    @Override
    public void addFavourite(String id) {
        String[] listaActual = settingsController.getFavourites();
        String[] listaNueva = new String[listaActual.length + 1];
        for(int i = 0; i < listaActual.length; i++) {
            listaNueva[i] = listaActual[i];
        }
        listaActual[listaNueva.length - 1] = id;
        settingsController.setFavourites(listaNueva);
    }

    @Override
    public void removeFavourite(String id) {

    }

    @Override
    public List<EstacionServicio> getEESSByIds(int[] ids) {
        return dataController.getByIds(ids);
    }

    @Override
    public FuelType getSettingFavouriteFuel() {
        return settingsController.getFavouriteFuel();
    }

    @Override
    public void setSettingFavouriteFuel(FuelType fuelType) {
        settingsController.setFavouriteFuel(fuelType);
    }

    @Override
    public OrderType getSettingOrder() {
        return settingsController.getFavouriteOrder();
    }

    @Override
    public void setSettingOrder(OrderType orderType) {
        settingsController.setFavouriteOrder(orderType);
    }

    @Override
    public double getSettingMaxDistance() {
        return settingsController.getMaxDistance();
    }

    @Override
    public void setSettingMaxDistance(double settingMaxDistance) {
        settingsController.setMaxDistance(settingMaxDistance);
    }
}
