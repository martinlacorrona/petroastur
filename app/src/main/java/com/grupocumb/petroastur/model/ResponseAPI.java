package com.grupocumb.petroastur.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAPI {

    @SerializedName("Fecha")
    @Expose
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    @Expose
    private List<EstacionServicio> estacionServicio = null;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<EstacionServicio> getListaEESSPrecio() {
        return estacionServicio;
    }

    public void setEstacionServicio(List<EstacionServicio> estacionServicio) {
        this.estacionServicio = estacionServicio;
    }

}
