package com.grupocumb.petroastur.ui.gallery;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.ui.home.HomeViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class GalleryFragment extends Fragment implements OnMapReadyCallback{

    private GalleryViewModel galleryViewModel;
    private GoogleMap gmap;
    private MapView mapView;
    private LocationManager locationManager;

    private boolean permisos;
    private Marker posUsuario;
    private String coordenada = "";
    private Context context;
    private EditText editText;
    private String direccion = "";
    private Location loc;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        homeViewModel =
                ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryViewModel.getText().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location s) {
            }
        });
        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(isConnected((Context)getContext())) {
            MapsInitializer.initialize(getContext());
            gmap = googleMap;
            gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            if (validaPermisos()) {
                if (checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle(R.string.nopermisos);
                    dialog.setMessage(R.string.nopermisos2);
                    dialog.create().show();
                    return;
                }
                gmap.setMyLocationEnabled(true);
                gmap.getUiSettings().setMyLocationButtonEnabled(true);

////                //LatLng centro = new LatLng(40, -3);

                //locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                //loc=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    LatLng centro = new LatLng(43.3602900,-5.8447600);
                    gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 9.5f));


                gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //CargarMarker(latLng);
                    }
                });
                List<EstacionServicio> estaciones=((MainActivity)getActivity()).getAppController().getAllEESSOrdered();
                if (estaciones!=null){
                    for (EstacionServicio e:estaciones){
                        //Necesitamos la altitud y longitud de las estaciones de servicio
                        LatLng c=new LatLng(Integer.getInteger(e.getLatitud()),Integer.getInteger(e.getLongitudWGS84()));
                        CargarMarker(c,e);
                    }
                }

            }
        } else {
            final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(context);
            alertOpciones.setTitle("No hay conexión a internet");
            alertOpciones.setMessage("Conéctate a una red para poder acceder al mapa");
            alertOpciones.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertOpciones.create().show();
        }

    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }


    private void CargarMarker(LatLng latLng, EstacionServicio e){
        coordenada = latLng.latitude + "," + latLng.longitude;
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        MarkerOptions marcadorOpciones = new MarkerOptions().position(latLng).title(e.getDireccion());
        if(posUsuario != null){
            posUsuario.remove();
        }
        posUsuario = gmap.addMarker(marcadorOpciones);
        posUsuario.showInfoWindow();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(14)
                .build();
        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
        gmap.animateCamera(cu);
    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]== PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                permisos = true;
            }else{
                permisos = false;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(R.string.nopermisos);
                dialog.setMessage(R.string.nopermisos2);
                dialog.create().show();
            }
        }
    }
}