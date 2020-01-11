package com.grupocumb.petroastur.controller.impl;

import android.content.Context;

import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.SettingsController;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;
import com.grupocumb.petroastur.service.SettingsService;
import com.grupocumb.petroastur.service.impl.SettingsServiceImpl;

import java.util.Calendar;
import java.util.Date;

public class SettingsControllerImpl implements SettingsController {

    private SettingsService settingsService;
    private Context context; //referenced context

    public SettingsControllerImpl(Context context) {
        this.context = context;
        this.settingsService = new SettingsServiceImpl(context);
    }

    @Override
    public Date getLastUpddate() {
        String settingName = context.getString(R.string.SETTINGS_LAST_UPDATE);
        String settingLastUpdate = settingsService.getSetting(settingName);

        if (settingLastUpdate != null && settingLastUpdate != "") { //tenemos fecha
            Calendar cal = Calendar.getInstance();
            String[] date = settingLastUpdate.split("-");
            cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
            cal.set(Calendar.MONTH, Integer.parseInt(date[1]));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
            return cal.getTime();
        }
        //esta vacio, devolvemos la fecha de 1970, y asi tendra que actualizar si o si
        return new Date();
    }

    @Override
    public void setLastUpdate(Date date) {
        String settingName = context.getString(R.string.SETTINGS_LAST_UPDATE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formattedDate =
                cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH)
                        + "-" + cal.get(Calendar.DAY_OF_MONTH);
        settingsService.setSetting(settingName, formattedDate);
    }

    @Override
    public String[] getFavourites() {
        String settingName = context.getString(R.string.SETTINGS_ID_FAVOURITE_ES);
        //las listas de favoritos se guardan como [0-1-3-4] Separado por guiones
        String value = settingsService.getSetting(settingName);
        if (value == null) //Si devuelve null es que nunca se creo nada.
            return new String[0];
        return value.split("-");
    }

    @Override
    public void setFavourites(String[] favourites) {
        String settingName = context.getString(R.string.SETTINGS_ID_FAVOURITE_ES);
        StringBuilder formattedFavourites = new StringBuilder();

        for (int i = 0; i < favourites.length; i++) {
            formattedFavourites.append(favourites[i]);
            if (i != favourites.length - 1) //si es la ultima no agrega guion
                formattedFavourites.append("-");
        }

        settingsService.setSetting(settingName, formattedFavourites.toString());
    }

    @Override
    public FuelType getFavouriteFuel() {
        String settingName = context.getString(R.string.SETTINGS_NAME_FAVOURITE_FUEL);
        String value = settingsService.getSetting(settingName);
        if (value == null)
            return FuelType.GASOLEO_A;
        try { //Si no encuentra el que deberia
            return FuelType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return FuelType.GASOLEO_A;
        }
    }

    @Override
    public void setFavouriteFuel(FuelType fuel) {
        String settingName = context.getString(R.string.SETTINGS_NAME_FAVOURITE_FUEL);
        String formattedFavouriteFuel = fuel.toString();
        settingsService.setSetting(settingName, formattedFavouriteFuel);
    }

    @Override
    public OrderType getFavouriteOrder() {
        String settingName = context.getString(R.string.SETTINGS_ORDER_LIST);
        String value = settingsService.getSetting(settingName);
        if (value == null)
            return OrderType.PRECIO;
        try { //Si no encuentra el que deberia
            return OrderType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return OrderType.PRECIO;
        }
    }

    @Override
    public void setFavouriteOrder(OrderType order) {
        String settingName = context.getString(R.string.SETTINGS_ORDER_LIST);
        String favouriteOrderFormatted = order.toString();
        settingsService.setSetting(settingName, favouriteOrderFormatted);
    }

    @Override
    public double getMaxDistance() {
        String settingName = context.getString(R.string.SETTINGS_DISTANCE_MAX);
        String value = settingsService.getSetting(settingName);
        if (value == null)
            return Double.MAX_VALUE;
        return Double.parseDouble(value);
    }

    @Override
    public void setMaxDistance(double distance) {
        String settingName = context.getString(R.string.SETTINGS_DISTANCE_MAX);
        String maxDistance = Double.toString(distance);
        settingsService.setSetting(settingName, maxDistance);
    }
}
