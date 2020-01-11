package com.grupocumb.petroastur;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.controller.impl.AppControllerImpl;
import com.grupocumb.petroastur.ui.gallery.GalleryFragment;
import com.grupocumb.petroastur.ui.home.HomeFragment;
import com.grupocumb.petroastur.ui.send.SendFragment;
import com.grupocumb.petroastur.ui.share.ShareFragment;
import com.grupocumb.petroastur.ui.slideshow.SlideshowFragment;
import com.grupocumb.petroastur.ui.task.ASyncBBDDLoader;
import com.grupocumb.petroastur.ui.tools.ToolsFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appController = new AppControllerImpl(getApplicationContext(), this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_mapa, R.id.nav_favoritas,
                R.id.nav_ajustes, R.id.nav_share, R.id.nav_send_opinion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        int id = menuItem.getItemId();

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.popBackStack();

                        if (id == R.id.nav_home) {
                            fragmentManager.beginTransaction().replace(R.id.container_o, new HomeFragment()).commit();
                        } else if (id == R.id.nav_mapa) {
                            if (estaConectadoInternet()) {
                                if (hayInternet()) {
                                    fragmentManager.beginTransaction().replace(R.id.container_o, new GalleryFragment()).commit();
                                } else {
                                    Toast.makeText(getApplicationContext(), "No hay conexión a internet.",
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "No está conectado a internet.",
                                        Toast.LENGTH_LONG).show();
                            }

                        } else if (id == R.id.nav_favoritas) {
                            fragmentManager.beginTransaction().replace(R.id.container_o, new SlideshowFragment()).commit();
                        } else if (id == R.id.nav_ajustes) {
                            fragmentManager.beginTransaction().replace(R.id.container_o, new ToolsFragment()).commit();
                        } else if (id == R.id.nav_share) {
                            if (estaConectadoInternet()) {
                                if (hayInternet()) {
                                    fragmentManager.beginTransaction().replace(R.id.container_o, new ShareFragment()).commit();
                                } else {
                                    Toast.makeText(getApplicationContext(), "No hay conexión a internet.",
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "No está conectado a internet.",
                                        Toast.LENGTH_LONG).show();
                            }

                        } else if (id == R.id.nav_send_opinion) {
                            fragmentManager.beginTransaction().replace(R.id.container_o, new SendFragment()).commit();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });

        //Tarea de background
        new ASyncBBDDLoader(this).execute();
    }

    private boolean hayInternet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private boolean estaConectadoInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public AppController getAppController() {
        return appController;
    }


    public void desactivarBarra() {
    }
}
