package com.grupocumb.petroastur.service;

public interface SettingsService {

    /**
     * Ponemos un valor a una opcion
     * @param setting opcion que es, debemos usar los que estan en la clase
     *                settings.Settings.java
     * @param value valor que le queremos dar.
     */
    void setSetting(String setting, String value);


    /**
     * Devuelve el valor de un setting
     * @param setting opcion que es, debemos usar los que estan en la clase
     *      *                settings.Settings.java
     * @return
     */
    String getSetting(String setting);
}
