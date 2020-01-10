package com.grupocumb.petroastur.controller.impl;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import com.grupocumb.petroastur.R;
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
    private Activity mainActivity; //referencia main activyte

    public AppControllerImpl(Context context, Activity mainActivity) {
        dataController = new DataControllerImpl(context);
        settingsController = new SettingsControllerImpl(context);
        this.mainActivity = mainActivity;
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
        OrderType favouriteOrder = settingsController.getFavouriteOrder();
        FuelType favouriteFuel = settingsController.getFavouriteFuel();

        if (favouriteOrder == OrderType.PRECIO) {
            return dataController.getAll().stream().parallel()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel)))
                    .collect(Collectors.toList());
        } else { //por distancia
            Location locationGPS = getLocation();
            return dataController.getAll().stream().parallel()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> {
                        Location locationES = new Location("LocationES");
                        locationES.setLatitude(Double.parseDouble(estacionServicio.getLatitud().replace(",", ".")));
                        locationES.setLongitude(Double.parseDouble(estacionServicio.getLongitudWGS84().replace(",", ".")));

                        return locationES.distanceTo(locationGPS);
                    }))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<EstacionServicio> getFavouritesOrdered() {
        String[] idsFavoritas = settingsController.getFavourites();
        List<EstacionServicio> favoritas = new ArrayList<EstacionServicio>();
        for(int i=0; i<idsFavoritas.length; i++) {
            if(! idsFavoritas[i].equals("null") && ! idsFavoritas[i].equals(""))
                favoritas.add(dataController.getById(Integer.parseInt(idsFavoritas[i])));
        }

        OrderType favouriteOrder = settingsController.getFavouriteOrder();
        FuelType favouriteFuel = settingsController.getFavouriteFuel();

        if (favouriteOrder == OrderType.PRECIO) {
            return favoritas.stream().parallel()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel)))
                    .collect(Collectors.toList());
        } else { //por distancia
            Location locationGPS = getLocation();
            return favoritas.stream().parallel()
                    .filter(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favouriteFuel) > 0.0)
                    .sorted(Comparator.comparingDouble(estacionServicio -> {
                        Location locationES = new Location("LocationES");
                        locationES.setLatitude(Double.parseDouble(estacionServicio.getLatitud().replace(",", ".")));
                        locationES.setLongitude(Double.parseDouble(estacionServicio.getLongitudWGS84().replace(",", ".")));

                        return locationES.distanceTo(locationGPS);
                    }))
                    .collect(Collectors.toList());
        }
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
        String[] listaActual = settingsController.getFavourites();
        String[] listaNueva = new String[listaActual.length-1];
        if(listaNueva.length != 0) {
            int j = 0;
            for (int i = 0; i < listaActual.length; i++) {
                if (! listaActual[i].equals(id)) {
                    listaNueva[j] = listaActual[i];
                    j++;
                }
            }
        }
        settingsController.setFavourites(listaNueva);
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

    /**
     * Metodo para sacar la localizacion, si esta desactivada, muestra Oviedo como el cnetro.
     * @return
     */
    private Location getLocation() {
        LocationManager locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);
        Location location;
        if (mainActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                mainActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //coge la localizacion del centro de oviedo.
            location = new Location("LocationGPS");
            location.setLatitude(43.3602900);
            location.setLongitude(-5.8447600);
        } else {
            //Tengo permisos, lo obtengo
            location = new Location("LocationGPS");
            //Chequear si tenemos ultima localizacion, puede ser que no...
            if(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null) {
                //Ponemos voiedo de nuevo si no va...
                location.setLatitude(43.3602900);
                location.setLongitude(-5.8447600);
            } else { //Los tiene
                location.setLatitude(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
                location.setLongitude(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
            }
        }
        return location;
    }
}
