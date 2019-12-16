package com.grupocumb.petroastur.service.impl;

import android.util.Log;

import com.grupocumb.petroastur.client.ReqResApi;
import com.grupocumb.petroastur.client.RetrofitClient;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.ResponseAPI;
import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.service.APIRequestService;
import com.grupocumb.petroastur.service.SQLService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequestServiceImpl implements APIRequestService {
    private ReqResApi clienteReqResAPI = RetrofitClient.getClient(ReqResApi.BASE_URL)
            .create(ReqResApi.class);

    private TransactionStatus status = TransactionStatus.WAITING;

    @Override
    public TransactionStatus getTransactionStatus() {
        return status;
    }

    @Override
    public void update(final SQLService sqlService) {
        Call<ResponseAPI> call = clienteReqResAPI.getEstaciones();
        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                switch (response.code()) {
                    case 200:
                        ResponseAPI data = response.body();
                        List<EstacionServicio> estaciones = data.getListaEESSPrecio();
                        sqlService.deleteAll();
                        sqlService.insertAll(estaciones);
                        status = TransactionStatus.DONE;
                        break;
                    default:
                        call.cancel();
                        status = TransactionStatus.FAILED;
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Log.e("Lista - error", t.toString());
                status = TransactionStatus.FAILED;
            }
        });
    }
}
