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
    private Button favorito;

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
        TextView nombre = root.findViewById(R.id.nombreEstacion);
        TextView direccion = root.findViewById(R.id.direccion);
        TextView carburantes = root.findViewById(R.id.carburantes);
        TextView horario = root.findViewById(R.id.tituloHorario);
        favorito = root.findViewById(R.id.añadirFavoritas);
        Button mostrarMapa = root.findViewById(R.id.mostrarMapaButton);

        nombre.setText(seleccionada.getEmpresa());
        direccion.setText(seleccionada.getDireccion() +
                "; " +
                seleccionada.getLocalidad());

        carburantes.setText(this.detailsToShow());
        horario.setText(seleccionada.getHorario());

        favorito.setOnClickListener(v -> {
            ((MainActivity) getActivity()).getAppController().addFavourite((seleccionada.getId()));
            Toast.makeText(
                    getActivity(),
                    "Se ha añadido a favoritos.",
                    Toast.LENGTH_SHORT)
                    .show();
            changeListenerRemove();
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

        mostrarMapa.setOnClickListener(v -> {
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

        });
        return root;
    }

    private boolean hayInternet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            return (val == 0);

        } catch (Exception e) {
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
            datos.append(" - Biodiesel: ").append(seleccionada.getPrecioBiodiesel()).append("€").append("\n");
        if (seleccionada.getPrecioBioetanol() != null)
            datos.append(" - Bioetanol: ").append(seleccionada.getPrecioBioetanol()).append("€").append("\n");
        if (seleccionada.getPrecioGasNaturalComprimido() != null)
            datos.append(" - Gas natural comprimido: ").append(seleccionada.getPrecioGasNaturalComprimido()).append("€").append("\n");
        if (seleccionada.getPrecioGasNaturalLicuado() != null)
            datos.append(" - Gas natural licuado: ").append(seleccionada.getPrecioGasNaturalLicuado()).append("€").append("\n");
        if (seleccionada.getPrecioGasesLicuadosDelPetroleo() != null)
            datos.append(" - Gases licuados petroleo: ").append(seleccionada.getPrecioGasesLicuadosDelPetroleo()).append("€").append("\n");
        if (seleccionada.getPrecioGasoleoA() != null)
            datos.append(" - Gasoleo A: ").append(seleccionada.getPrecioGasoleoA()).append("€").append("\n");
        if (seleccionada.getPrecioGasoleoB() != null)
            datos.append(" - Gasoleo B: ").append(seleccionada.getPrecioGasoleoB()).append("€").append("\n");
        if (seleccionada.getPrecioGasolina95Proteccion() != null)
            datos.append(" - Gasolina 95: ").append(seleccionada.getPrecioGasolina95Proteccion()).append("€").append("\n");
        if (seleccionada.getPrecioGasolina98() != null)
            datos.append(" - Gasolina 98: ").append(seleccionada.getPrecioGasolina98()).append("€").append("\n");
        if (seleccionada.getPrecioNuevoGasoleoA() != null)
            datos.append(" - Nuevo Gasoleo A: ").append(seleccionada.getPrecioNuevoGasoleoA()).append("€").append("\n");

        return datos.toString();
    }

    private void changeListenerRemove() {
        favorito.setText(R.string.eliminar_favoritas);
        favorito.setOnClickListener(v -> {
            ((MainActivity) getActivity()).getAppController().removeFavourite((seleccionada.getId()));
            Toast.makeText(
                    getActivity(),
                    "Se ha eliminado de favoritos.",
                    Toast.LENGTH_SHORT)
                    .show();
            changeListenerAdd();
        });
    }

    private void changeListenerAdd() {
        favorito.setText(R.string.añadir_favoritas);
        favorito.setOnClickListener(v -> {
            ((MainActivity) getActivity()).getAppController().addFavourite((seleccionada.getId()));
            Toast.makeText(
                    getActivity(),
                    "Se ha añadido a favoritos.",
                    Toast.LENGTH_SHORT)
                    .show();
            changeListenerRemove();
        });
    }
}