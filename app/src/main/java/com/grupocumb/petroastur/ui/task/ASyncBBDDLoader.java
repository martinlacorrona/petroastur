package com.grupocumb.petroastur.ui.task;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.List;

public class ASyncBBDDLoader extends AsyncTask {

    private AppController appController;
    private MainActivity activity;

    public ASyncBBDDLoader(MainActivity activity) {
        this.activity = activity;
        this.appController = activity.getAppController();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //TODO: aqui deberia de lanzar el fragment de cargando
        //aqui

        //TODO: esta linea no funciona, fueron pruebas
        this.activity.getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment());

        activity.runOnUiThread(() -> Toast.makeText(activity, "Actualizando gasolineras...",
                Toast.LENGTH_SHORT).show());
        while(appController.isUpdated() == TransactionStatus.WAITING) {
            //LOADING...
        }
        if(appController.isUpdated() == TransactionStatus.DONE) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Gasolineras actualizadas.",
                    Toast.LENGTH_SHORT).show());
            //TODO: justo antes de acabar, ahora lanzar el fragment de la lista, ya que estaria todo cargado
        } else if(appController.isUpdated() == TransactionStatus.FAILED) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Ocurrio un error a la hora de actualizar.",
                    Toast.LENGTH_LONG).show());
            //TODO: lanzar en la pantalla de carga error, y que tiene que reiniciar la app y tener inernet
        }
        return null;
    }
}
