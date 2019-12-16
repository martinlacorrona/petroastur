package com.grupocumb.petroastur.service.impl;

import android.util.Log;

import com.grupocumb.petroastur.client.ReqResApi;
import com.grupocumb.petroastur.client.RetrofitClient;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.ResponseAPI;
import com.grupocumb.petroastur.service.APIRequestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequestServiceImpl implements APIRequestService {
    private ReqResApi clienteReqResAPI = RetrofitClient.getClient(ReqResApi.BASE_URL)
            .create(ReqResApi.class);
    private List<EstacionServicio> estaciones = new ArrayList<EstacionServicio>();

    @Override
    public List<EstacionServicio> getAll() {
        Call<ResponseAPI> call = clienteReqResAPI.getEstaciones();
        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                switch (response.code()) {
                    case 200:
                        ResponseAPI data = response.body();
                        estaciones = data.getListaEESSPrecio();
                        break;
                    default:
                        call.cancel();
                        break;
                }
            }
            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Log.e("Lista - error", t.toString());
            }
        });
        return estaciones;
    }
}
