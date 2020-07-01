package com.grupocumb.petroastur.util;

import com.google.android.gms.maps.model.LatLng;
import com.grupocumb.petroastur.model.ClusterEstacionServicio;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;

public class ClusterEstacionServicioAdapter {
    public static ClusterEstacionServicio convertEstacionServicioToCluster(EstacionServicio e, FuelType favorito) {
        Double precio = e.getPrecioCombustible(favorito);
        return new ClusterEstacionServicio(
                new LatLng(
                Double.parseDouble(e.getLatitud().replace(",", ".")),
                Double.parseDouble(e.getLongitudWGS84().replace(",", "."))),
                e.getEmpresa(),
                "Precio " + favorito.getFormattedName() + ": " + precio.toString().replace(".", ",") + "€",
                e);
    }
}
