package com.grupocumb.petroastur.controller.impl;

import android.content.Context;

import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.controller.DataController;
import com.grupocumb.petroastur.controller.SettingsController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppControllerImpl implements AppController {

    private DataController dataController;
    private SettingsController settingsController;

    public AppControllerImpl(Context context) {
        dataController = new DataControllerImpl(context);
        settingsController = new SettingsControllerImpl(context);
        this.updateAll();
    }

    private void updateAll() {
        dataController.update();
        settingsController.setLastUpdate(new Date());
    }

    @Override
    public List<EstacionServicio> getAllEESS() {
        return dataController.getAll();
    }

    @Override
    public List<EstacionServicio> getAllEESSOrdered() {
        //TODO VALORES PARA TEST, CAMBIAR !!!!!!!!!!!!!!!!
        OrderType favouriteOrder = OrderType.PRECIO;//settingsController.getFavouriteOrder();
        FuelType favouriteFuel = settingsController.getFavouriteFuel();

        if (favouriteOrder == OrderType.PRECIO) {
            return dataController.getAll().stream()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel)))
                    .collect(Collectors.toList());
        } else {
            // TODO ORDEN POR DISTANCIA
            return null;
        }
    }

    @Override
    public List<EstacionServicio> getFavouritesOrdered() {
        String[] idsFavoritas = settingsController.getFavourites();
        List<EstacionServicio> favoritas = new ArrayList<EstacionServicio>();
        for(int i=0; i<idsFavoritas.length; i++) {
            if(! idsFavoritas[i].equals("null"))
                favoritas.add(dataController.getById(Integer.parseInt(idsFavoritas[i])));
        }
        // TODO ORDENACIÓN
        return favoritas;
    }

    @Override
    public void addFavourite(String id) {
        String[] listaActual = settingsController.getFavourites();
        String[] listaNueva;
        if(listaActual.length == 0) {
            listaNueva = new String[1];
            listaNueva[0] = id;
        }
        else {
            listaNueva = new String[listaActual.length + 1];
            for (int i = 0; i < listaActual.length; i++) {
                listaNueva[i] = listaActual[i];
            }
            listaNueva[listaNueva.length - 1] = id;
        }
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

    @Override
    public TransactionStatus isUpdated() {
        return dataController.isUpdated();
    }
}
