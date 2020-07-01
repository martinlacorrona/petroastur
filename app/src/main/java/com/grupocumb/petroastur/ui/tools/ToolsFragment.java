package com.grupocumb.petroastur.ui.tools;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;
import com.grupocumb.petroastur.ui.home.HomeFragment;

public class ToolsFragment extends Fragment {

    private AppController ap;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        ap = ((MainActivity) getActivity()).getAppController();

        RadioGroup radioButtonCombustPreferido = root.findViewById(R.id.grupoCarburante);
        RadioButton bioetanol = root.findViewById(R.id.bioEtanolButton);
        RadioButton gas_natural_comprimido = root.findViewById(R.id.gasNaturalComprimidoButton);
        RadioButton gas_natural_licuado = root.findViewById(R.id.gasNaturalLicuadoButton);
        RadioButton gases_licuados_petroleo = root.findViewById(R.id.gasesLicuadosDelPetroleoButton);
        RadioButton gasoleo_a = root.findViewById(R.id.gasoleoAButton);
        RadioButton gasoleo_b = root.findViewById(R.id.gasoleoBButton);
        RadioButton gasoleo95 = root.findViewById(R.id.gasolina95Button);
        RadioButton gasoleo1_98 = root.findViewById(R.id.gasolina98Button);
        RadioButton nuevo_gasoleo_a = root.findViewById(R.id.nuevoGasoleoA);

        RadioGroup radioButtonListado = root.findViewById(R.id.grupoListado);
        RadioButton ordenDista = root.findViewById(R.id.distanc);
        RadioButton ordenPrecio = root.findViewById(R.id.preci);

        RadioGroup radioButtonDistancia = root.findViewById(R.id.grupoDistancia);
        RadioButton distancia1 = root.findViewById(R.id.disMax1);
        RadioButton distancia2 = root.findViewById(R.id.distaMax_2);
        RadioButton r = root.findViewById(R.id.sin_maxima);

        if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.BIOETANOL) {
            bioetanol.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GAS_NATURAL_COMPRIMIDO) {
            gas_natural_comprimido.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GAS_NATURAL_LICUADO) {
            gas_natural_licuado.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASES_LICUADOS_PETROLEO) {
            gases_licuados_petroleo.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLEO_A) {
            gasoleo_a.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLEO_B) {
            gasoleo_b.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLINA_95) {
            gasoleo95.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLINA_98) {
            gasoleo1_98.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.NUEVO_GASOLEO_A) {
            nuevo_gasoleo_a.setChecked(true);
        }

        if (((MainActivity) getActivity()).getAppController().getSettingOrder() == OrderType.DISTANCIA) {
            ordenDista.setChecked(true);
        } else {
            ordenPrecio.setChecked(true);
        }

        if (((MainActivity) getActivity()).getAppController().getSettingMaxDistance() == 20) {
            distancia1.setChecked(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingMaxDistance() == 50) {
            distancia2.setChecked(true);
        } else {
            r.setChecked(true);
        }

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

        radioButtonCombustPreferido.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.bioEtanolButton) {
                ap.setSettingFavouriteFuel(FuelType.BIOETANOL);
            } else if (checkedId == R.id.gasNaturalComprimidoButton) {
                ap.setSettingFavouriteFuel(FuelType.GAS_NATURAL_COMPRIMIDO);
            } else if (checkedId == R.id.gasNaturalLicuadoButton) {
                ap.setSettingFavouriteFuel(FuelType.GAS_NATURAL_LICUADO);
            } else if (checkedId == R.id.gasesLicuadosDelPetroleoButton) {
                ap.setSettingFavouriteFuel(FuelType.GASES_LICUADOS_PETROLEO);
            } else if (checkedId == R.id.gasoleoAButton) {
                ap.setSettingFavouriteFuel(FuelType.GASOLEO_A);
            } else if (checkedId == R.id.gasoleoBButton) {
                ap.setSettingFavouriteFuel(FuelType.GASOLEO_B);
            } else if (checkedId == R.id.gasolina95Button) {
                ap.setSettingFavouriteFuel(FuelType.GASOLINA_95);
            } else if (checkedId == R.id.gasolina98Button) {
                ap.setSettingFavouriteFuel(FuelType.GASOLINA_98);
            } else {
                ap.setSettingFavouriteFuel(FuelType.NUEVO_GASOLEO_A);
            }
            return;
        });

        radioButtonListado.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.preci) {
                ap.setSettingOrder(OrderType.PRECIO);
            } else {
                if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle(R.string.nopermisos);
                    dialog.setMessage(R.string.nopermisos2);
                    dialog.create().show();
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
                ap.setSettingOrder(OrderType.DISTANCIA);
            }
            return;
        });

        radioButtonDistancia.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.disMax1) {
                ap.setSettingMaxDistance(20);
            } else if (checkedId == R.id.distaMax_2) {
                ap.setSettingMaxDistance(50);
            } else {
                ap.setSettingMaxDistance(100);
            }
            return;
        });
        return root;
    }
}