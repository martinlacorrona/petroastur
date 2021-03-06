package com.grupocumb.petroastur.ui.gallery;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.ClusterEstacionServicio;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.ui.detallada.DetalladaFragment;
import com.grupocumb.petroastur.util.ClusterEstacionServicioAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class GalleryFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gmap;
    private LocationManager locationManager;
    private ClusterManager<ClusterEstacionServicio> mClusterManager;

    private Marker posUsuario;
    private String coordenada = "";
    private Context context;
    private EditText editText;
    private String direccion = "";
    private Location loc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (isConnected(getContext())) {
            MapsInitializer.initialize(getContext());
            gmap = googleMap;
            mClusterManager = new ClusterManager(getContext(), gmap);
            mClusterManager.setRenderer(new ClusterEstacionServicioRendered(getContext(), gmap, mClusterManager));
            gmap.setOnCameraIdleListener(mClusterManager);
            gmap.setOnMarkerClickListener(mClusterManager);

            addItems();

            LatLng centro = new LatLng(43.3602900, -5.8447600);
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 5f));

            mClusterManager.setOnClusterItemInfoWindowClickListener(listener -> {
                DetalladaFragment fr = new DetalladaFragment(listener.getEstacionServicio());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_o, fr)
                        .addToBackStack(null)
                        .commit();
            });

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

                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (loc == null) { //No tiene la loc activada, la mandamos a oviedo
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(centro, 10f));
                } else {
                    LatLng gpsUserPos = new LatLng(loc.getLatitude(), loc.getLongitude());
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(gpsUserPos, 13f));
                }

            }
        } else {
            if(getContext() != null) {
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(context);
                alertOpciones.setTitle("No hay conexión a internet");
                alertOpciones.setMessage("Conéctate a una red para poder acceder al mapa");
                alertOpciones.setPositiveButton("Aceptar", (dialog, which) -> {
                });
                alertOpciones.create().show();
            }
        }
    }

    private void addItems() {
        List<EstacionServicio> estaciones = ((MainActivity) getActivity()).getAppController().getAllEESSOrdered();
        FuelType favorito = ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel();

        Double precioMaximo;
        try {
            precioMaximo = estaciones.stream().parallel()
                    .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                    .max(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);
        } catch (NoSuchElementException e) {
            precioMaximo = 2.50;
        }
        Double precioMinimo;
        try {
            precioMinimo = estaciones.stream().parallel()
                    .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                    .min(Comparator.comparingDouble(estacionServicio -> estacionServicio
                            .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);
        } catch (NoSuchElementException e) {
            precioMinimo = 0.50;
        }

        Double diferenciaMaximoMinimo = precioMaximo - precioMinimo;
        Double diferenciaEnTresPartes = diferenciaMaximoMinimo / 3;
        Double precioLimiteHastaVerde = precioMinimo + diferenciaEnTresPartes * 1;
        Double precioLimiteHastaAmarillo = precioMinimo + diferenciaEnTresPartes * 2;

        estaciones.stream().parallel().forEach(estacionServicio -> {
            Double precio = estacionServicio.getPrecioCombustible(favorito);
            BitmapDescriptor icon;
            if (precio < precioLimiteHastaVerde)
                icon = BitmapDescriptorFactory.fromResource(R.drawable.preciobajo);
            else if (precio >= precioLimiteHastaVerde && precio < precioLimiteHastaAmarillo)
                icon = BitmapDescriptorFactory.fromResource(R.drawable.preciomedio);
            else
                icon = BitmapDescriptorFactory.fromResource(R.drawable.precioalto);

            ClusterEstacionServicio cluster = ClusterEstacionServicioAdapter.convertEstacionServicioToCluster(estacionServicio, favorito, icon);
            mClusterManager.addItem(cluster);
        });
    }

    private class ClusterEstacionServicioRendered extends DefaultClusterRenderer<ClusterEstacionServicio> {

        public ClusterEstacionServicioRendered(Context context, GoogleMap map, ClusterManager<ClusterEstacionServicio> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(@NonNull ClusterEstacionServicio item, @NonNull MarkerOptions markerOptions) {
            markerOptions
                    .icon(item.getIcon())
                    .title(item.getEstacionServicio().getEmpresa())
                    .snippet(item.getSnippet());;
        }

        @Override
        protected void onClusterItemUpdated(@NonNull ClusterEstacionServicio item, @NonNull Marker marker) {
            marker
                    .setIcon(item.getIcon())
            ;
        }
    }

    public boolean isConnected(Context context) {
        if(context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netinfo = cm.getActiveNetworkInfo();

            if (netinfo != null && netinfo.isConnectedOrConnecting()) {
                android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
            } else
                return false;
        }
        return false;
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
            boolean permisos;
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permisos = true;


                if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gmap.setMyLocationEnabled(true);
                gmap.getUiSettings().setMyLocationButtonEnabled(true);

                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if(loc == null) { //Al aceptar la localizacion hay algunos dipositivos que petan
                    //Por si acaso le mando a oviedo, la siguiente vez funcionara
                    LatLng centro = new LatLng(43.3602900, -5.8447600);
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(centro, 10f));
                } else {
                    LatLng gpsUserPos = new LatLng(loc.getLatitude(), loc.getLongitude());
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(gpsUserPos, 13f));
                }

            } else {
                permisos = false;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(R.string.nopermisos);
                dialog.setMessage(R.string.nopermisos2);
                dialog.create().show();

                LatLng centro = new LatLng(43.3602900, -5.8447600);
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(centro, 10f));
            }
        }
    }
}