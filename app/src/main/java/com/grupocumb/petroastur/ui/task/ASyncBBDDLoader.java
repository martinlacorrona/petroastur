package com.grupocumb.petroastur.ui.task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.AsyncTaskLoader;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.ui.cargando.CargandoFragment;
import com.grupocumb.petroastur.ui.error.ErrorFragment;
import com.grupocumb.petroastur.ui.home.HomeFragment;

import java.util.List;
import java.util.logging.ErrorManager;

public class ASyncBBDDLoader extends AsyncTask {

    private AppController appController;
    private MainActivity activity;

    public ASyncBBDDLoader(MainActivity activity) {
        this.activity = activity;
        this.appController = activity.getAppController();
    }

    @SuppressLint("WrongThread")
    @Override
    protected Object doInBackground(Object[] objects) {
        //TODO: aqui deberia de lanzar el fragment de cargando
        //aqui
        DrawerLayout d=(DrawerLayout)this.activity.findViewById(R.id.drawer_layout);
        CargandoFragment fr=new CargandoFragment();
                this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fr).commit();
        d.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //TODO: esta linea no funciona, fueron pruebas
        //this.activity.getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment());

        activity.runOnUiThread(() -> Toast.makeText(activity, "Actualizando gasolineras...",
                Toast.LENGTH_SHORT).show());
        while(appController.isUpdated() == TransactionStatus.WAITING) {
            //LOADING...
        }
        if(appController.isUpdated() == TransactionStatus.DONE) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Gasolineras actualizadas.",
                    Toast.LENGTH_SHORT).show());
            //TODO: justo antes de acabar, ahora lanzar el fragment de la lista, ya que estaria todo cargado
            this.activity.getSupportFragmentManager().beginTransaction().remove(fr);
            HomeFragment home=new HomeFragment();
            this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,home).commit();
            d.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else if(appController.isUpdated() == TransactionStatus.FAILED) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Ocurrio un error a la hora de actualizar.",
                    Toast.LENGTH_LONG).show());
            //TODO: lanzar en la pantalla de carga error, y que tiene que reiniciar la app y tener inernet
            this.activity.getSupportFragmentManager().beginTransaction().remove(fr);
            ErrorFragment home=new ErrorFragment();
            this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,home).commit();
            d.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        return null;
    }
}
