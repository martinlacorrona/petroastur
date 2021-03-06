package com.grupocumb.petroastur.ui.task;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.ui.cargando.CargandoFragment;
import com.grupocumb.petroastur.ui.error.ErrorFragment;
import com.grupocumb.petroastur.ui.home.HomeFragment;

public class ASyncBBDDLoader extends AsyncTask<Void, Void, Void> {

    private AppController appController;
    private MainActivity activity;

    public ASyncBBDDLoader(MainActivity activity) {
        this.activity = activity;
        this.appController = activity.getAppController();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        CargandoFragment fr = new CargandoFragment();
        this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_o, fr).addToBackStack("y").commit();
        this.activity.desactivarBarra();

        activity.runOnUiThread(() -> Toast.makeText(activity, "Actualizando gasolineras...",
                Toast.LENGTH_SHORT).show());
        while (appController.isUpdated() == TransactionStatus.WAITING) {
            //LOADING...
        }
        if (appController.isUpdated() == TransactionStatus.DONE) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Gasolineras actualizadas.",
                    Toast.LENGTH_SHORT).show());
            HomeFragment home = new HomeFragment();
            this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_o, home).commit();
        } else if (appController.isUpdated() == TransactionStatus.FAILED) {
            activity.runOnUiThread(() -> Toast.makeText(activity, "Ocurrio un error a la hora de actualizar.",
                    Toast.LENGTH_LONG).show());
            this.activity.getSupportFragmentManager().popBackStack();
            HomeFragment homeFragment = new HomeFragment();
            ErrorFragment errorFragment = new ErrorFragment();
            this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_o, homeFragment).commit();
            this.activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_o, errorFragment).addToBackStack(null).commit();
        }
        return null;
    }
}
