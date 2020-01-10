package com.grupocumb.petroastur.ui.detallada;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.ui.gallery.GalleryViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class MapaDetalladoFragment extends Fragment implements OnMapReadyCallback {

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
    private EstacionServicio e;

    public MapaDetalladoFragment(EstacionServicio e) {
        this.e = e;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


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
        if (isConnected((Context) getContext())) {
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
                //LatLng centro = new LatLng(40, -3);
                LatLng centro = new LatLng(Integer.getInteger(e.getLatitud()), Integer.getInteger(e.getLongitudWGS84()));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 15.5f));
                gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //CargarMarker(latLng);
                    }
                });

//                if (!coordenada.equals("")) {
//                    String[] aux = coordenada.split(",");
//                    double latitud = Double.parseDouble(aux[0]);
//                    double longitud = Double.parseDouble(aux[1]);
//                    LatLng latLng = new LatLng(latitud, longitud);
//                    CargarMarker(latLng);
//                }
            }
        } else {
            final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(context);
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
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }


    private void CargarMarker(LatLng latLng) {
        coordenada = latLng.latitude + "," + latLng.longitude;
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1);
        } catch (IOException ioException) {
            Log.e("ERROR", "IO", ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            Log.e("ERROR", "ILEGALARGUMENT", illegalArgumentException);
        }
        Address address = addresses.get(0);
        ArrayList<String> addressFragments = new ArrayList<String>();
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getContext(), "No se han encontrado resultados.", Toast.LENGTH_LONG).show();
        } else {

            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
        }
        MarkerOptions marcadorOpciones = new MarkerOptions().position(latLng).title(addressFragments.get(0));
        direccion = addressFragments.get(0);
        if (posUsuario != null) {
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

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if ((checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permisos = true;
            } else {
                permisos = false;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(R.string.nopermisos);
                dialog.setMessage(R.string.nopermisos2);
                dialog.create().show();
            }
        }
    }
}