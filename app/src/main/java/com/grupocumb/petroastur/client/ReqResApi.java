package com.grupocumb.petroastur.client;

import com.grupocumb.petroastur.model.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReqResApi {
    String BASE_URL = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/" +
            "PreciosCarburantes/EstacionesTerrestres/";

    /**
     * Petición get al servicio
     * 03 -> Código Asturias
     * @return Objeto ResponseApi con la lista de estaciones de servicio
     */
    @GET("FiltroCCAA/03")
    Call<ResponseAPI> getEstaciones();
}
