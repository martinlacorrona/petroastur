package com.grupocumb.petroastur.ui;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.List;

public class ASyncBBDDLoader extends AsyncTask {

    private AppController appController;
    private MainActivity activity;

    //TODO: borrar si no se usa ya que se esta implementando uno mejor
    public ASyncBBDDLoader(AppController appController, MainActivity activity) {
        this.appController = appController;
        this.activity = activity;
    }

    public ASyncBBDDLoader(MainActivity activity) {
        this.activity = activity;
        this.appController = activity.getAppController();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        activity.runOnUiThread(() -> Toast.makeText(activity, "Actualizando gasolineras...",
                Toast.LENGTH_SHORT).show());
        while(appController.isUpdated() == TransactionStatus.WAITING) {
            //LOADING...
        }
        if(appController.isUpdated() == TransactionStatus.DONE) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Gasolineras actualizadas.",
                    Toast.LENGTH_SHORT).show());
        } else if(appController.isUpdated() == TransactionStatus.FAILED) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Ocurrio un error a la hora de actualizar.",
                    Toast.LENGTH_LONG).show());
        }
        return null;
    }
}
