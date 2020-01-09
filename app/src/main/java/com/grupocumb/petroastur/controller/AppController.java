package com.grupocumb.petroastur.controller;

import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.List;

public interface AppController {
    /**
     * Devuelve todas las estaciones de servicio
     *
     * @return
     */
    List<EstacionServicio> getAllEESS();

    /**
     * Devuelve las estacion de servicio ordenadas segun el valor del setting.
     *
     * @return
     */
    List<EstacionServicio> getAllEESSOrdered();

    /**
     * Devuelve lista de favoritos ordenados en funcion del setting
     *
     * @return
     */
    List<EstacionServicio> getFavouritesOrdered();

    /**
     * Añade un favorito, por id.
     *
     * @param id
     */
    void addFavourite(String id);

    /**
     * Borra un favorito.
     *
     * @param id
     */
    void removeFavourite(String id);

    /**
     * Devuelve las estaciones de servicio por id.
     *
     * @param id
     * @return
     */
    List<EstacionServicio> getEESSByIds(int[] id);

    /**
     * Devuelve el fuel favorito.
     *
     * @return
     */
    FuelType getSettingFavouriteFuel();

    /**
     * Cambiamos el fuel favourite.
     *
     * @param fuelType
     */
    void setSettingFavouriteFuel(FuelType fuelType);

    /**
     * Devuelve el valor que hay el opcion de ordenacion.
     *
     * @return
     */
    OrderType getSettingOrder();

    /**
     * Setea el valor para la ordenacion en las settings.
     *
     * @param orderType
     */
    void setSettingOrder(OrderType orderType);

    /**
     * Devuelve la setting de max distance.
     *
     * @return
     */
    double getSettingMaxDistance();

    /**
     * Setea la setting de max distance.
     *
     * @param settingMaxDistance
     */
    void setSettingMaxDistance(double settingMaxDistance);

    /**
     * Indica si los datos están o no actualizados
     *
     * @return TransactionStatus
     */
    TransactionStatus isUpdated();
}
