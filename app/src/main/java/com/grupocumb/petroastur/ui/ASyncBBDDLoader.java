package com.grupocumb.petroastur.ui;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.List;

public class ASyncBBDDLoader extends AsyncTaskLoader<List<EstacionServicio>> {

    private AppController appController;

    public ASyncBBDDLoader(Context context, AppController appController) {
        super(context);
        this.appController = appController;
    }

    @Nullable
    @Override
    public List<EstacionServicio> loadInBackground() {
        Toast.makeText(getContext(), "Actualizando gasolineras...", Toast.LENGTH_LONG).show();
        while(appController.isUpdated() == TransactionStatus.WAITING) {
            //LOADING...
        }
        if(appController.isUpdated() == TransactionStatus.DONE) {
            Toast.makeText(getContext(), "Gasolineras cargadas.", Toast.LENGTH_LONG).show();
        } else if(appController.isUpdated() == TransactionStatus.FAILED) {
            Toast.makeText(getContext(), "Ocurrio un error a la hora de actualizar.", Toast.LENGTH_LONG).show();
        }
        return appController.getAllEESSOrdered();
    }
}
