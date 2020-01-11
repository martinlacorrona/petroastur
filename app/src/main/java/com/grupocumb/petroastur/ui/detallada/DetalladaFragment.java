package com.grupocumb.petroastur.ui.detallada;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public class DetalladaFragment extends Fragment {

    private EstacionServicio seleccionada;
    private TextView nombre;
    private TextView direccion;
    private TextView carburantes;
    private TextView horario;
    private Button favorito;
    private Button mostrarMapa;

    public DetalladaFragment(EstacionServicio estacionServicio) {
        this.seleccionada = estacionServicio;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detallada, container, false);
        nombre = (TextView) root.findViewById(R.id.nombreEstacion);
        direccion = (TextView) root.findViewById(R.id.direccion);
        carburantes = (TextView) root.findViewById(R.id.carburantes);
        horario = (TextView) root.findViewById(R.id.tituloHorario);
        favorito = (Button) root.findViewById(R.id.añadirFavoritas);
        mostrarMapa = (Button) root.findViewById(R.id.mostrarMapaButton);

        nombre.setText(seleccionada.getEmpresa());
        direccion.setText(seleccionada.getDireccion() +
                "; " +
                seleccionada.getLocalidad());

        carburantes.setText(this.detailsToShow());
        horario.setText(seleccionada.getHorario());

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getAppController().addFavourite((seleccionada.getId()));
                Toast.makeText(
                        getActivity(),
                        "Se ha añadido a favoritos.",
                        Toast.LENGTH_SHORT)
                        .show();
                changeListenerRemove();
            }
        });

        List<EstacionServicio> favoritos = ((MainActivity) getActivity())
                .getAppController()
                .getFavouritesOrdered();
        for (EstacionServicio es : favoritos) {
            if (seleccionada.getId().equals(es.getId())) {
                this.changeListenerRemove();
                break;
            }
        }

        mostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estaConectadoInternet()) {
                    if (hayInternet()) {
                        MapaDetalladoFragment fr = new MapaDetalladoFragment(seleccionada);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_o, fr)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "No hay conexión a internet.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No está conectado a internet.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        return root;
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
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    private String detailsToShow() {
        StringBuilder datos = new StringBuilder();
        if (seleccionada.getPrecioBiodiesel() != null)
            datos.append("-Biodiesel: " + seleccionada.getPrecioBiodiesel() + "€" + "\n");
        if (seleccionada.getPrecioBioetanol() != null)
            datos.append("-Bioetanol: " + seleccionada.getPrecioBioetanol() + "€" + "\n");
        if (seleccionada.getPrecioGasNaturalComprimido() != null)
            datos.append("-Gas natural comprimido: " + seleccionada.getPrecioGasNaturalComprimido() + "€" + "\n");
        if (seleccionada.getPrecioGasNaturalLicuado() != null)
            datos.append("-Gas natural licuado: " + seleccionada.getPrecioGasNaturalLicuado() + "€" + "\n");
        if (seleccionada.getPrecioGasesLicuadosDelPetroleo() != null)
            datos.append("-Gases licuados petroleo: " + seleccionada.getPrecioGasesLicuadosDelPetroleo() + "€" + "\n");
        if (seleccionada.getPrecioGasoleoA() != null)
            datos.append("-Gasoleo A: " + seleccionada.getPrecioGasoleoA() + "€" + "\n");
        if (seleccionada.getPrecioGasoleoB() != null)
            datos.append("-Gasoleo B: " + seleccionada.getPrecioGasoleoB() + "€" + "\n");
        if (seleccionada.getPrecioGasolina95Proteccion() != null)
            datos.append("-Gasolina 95: " + seleccionada.getPrecioGasolina95Proteccion() + "€" + "\n");
        if (seleccionada.getPrecioGasolina98() != null)
            datos.append("-Gasolina 98: " + seleccionada.getPrecioGasolina98() + "€" + "\n");
        if (seleccionada.getPrecioNuevoGasoleoA() != null)
            datos.append("-Nuevo Gasoleo A: " + seleccionada.getPrecioNuevoGasoleoA() + "€" + "\n");

        return datos.toString();
    }

    private void changeListenerRemove() {
        favorito.setText(R.string.eliminar_favoritas);
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getAppController().removeFavourite((seleccionada.getId()));
                Toast.makeText(
                        getActivity(),
                        "Se ha eliminado de favoritos.",
                        Toast.LENGTH_SHORT)
                        .show();
                changeListenerAdd();
            }
        });
    }

    private void changeListenerAdd() {
        favorito.setText(R.string.añadir_favoritas);
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getAppController().addFavourite((seleccionada.getId()));
                Toast.makeText(
                        getActivity(),
                        "Se ha añadido a favoritos.",
                        Toast.LENGTH_SHORT)
                        .show();
                changeListenerRemove();
            }
        });
    }
}