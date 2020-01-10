package com.grupocumb.petroastur.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.AppController;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.model.OrderType;

public class ToolsFragment extends Fragment {
    private ToolsViewModel toolsViewModel;
    private RadioGroup radioButtonCombustPreferido;
    private RadioButton bioetanol;
    private RadioButton gas_natural_comprimido;
    private RadioButton gas_natural_licuado;
    private RadioButton gases_licuados_petroleo;
    private RadioButton gasoleo_a;
    private RadioButton gasoleo_b;
    private RadioButton gasoleo95;
    private RadioButton gasoleo1_98;
    private RadioButton nuevo_gasoleo_a;

    private RadioGroup radioButtonListado;
    private RadioButton ordenDista;
    private RadioButton ordenPrecio;

    private RadioGroup radioButtonDistancia;
    private RadioButton distancia1;
    private RadioButton distancia2;

    private AppController ap;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        ap = ((MainActivity) getActivity()).getAppController();

        radioButtonCombustPreferido = (RadioGroup) root.findViewById(R.id.grupoCarburante);
        bioetanol = (RadioButton) root.findViewById(R.id.bioEtanolButton);
        gas_natural_comprimido = (RadioButton) root.findViewById(R.id.gasNaturalComprimidoButton);
        gas_natural_licuado = (RadioButton) root.findViewById(R.id.gasNaturalLicuadoButton);
        gases_licuados_petroleo = (RadioButton) root.findViewById(R.id.gasesLicuadosDelPetroleoButton);
        gasoleo_a = (RadioButton) root.findViewById(R.id.gasoleoAButton);
        gasoleo_b = (RadioButton) root.findViewById(R.id.gasoleoBButton);
        gasoleo95 = (RadioButton) root.findViewById(R.id.gasolina95Button);
        gasoleo1_98 = (RadioButton) root.findViewById(R.id.gasolina98Button);
        nuevo_gasoleo_a = (RadioButton) root.findViewById(R.id.nuevoGasoleoA);

        radioButtonListado = (RadioGroup) root.findViewById(R.id.grupoListado);
        ordenDista = (RadioButton) root.findViewById(R.id.distanc);
        ordenPrecio = (RadioButton) root.findViewById(R.id.preci);

        radioButtonDistancia = (RadioGroup) root.findViewById(R.id.grupoDistancia);
        distancia1 = (RadioButton) root.findViewById(R.id.disMax1);
        distancia2 = (RadioButton) root.findViewById(R.id.distaMax_2);
        RadioButton r = (RadioButton) root.findViewById(R.id.sin_maxima);

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

        radioButtonCombustPreferido.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
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
            }
        });

        radioButtonListado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.preci) {
                    ap.setSettingOrder(OrderType.PRECIO);
                } else {
                    ap.setSettingOrder(OrderType.DISTANCIA);
                }
                return;
            }
        });

        radioButtonDistancia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.disMax1) {
                    ap.setSettingMaxDistance(20);
                } else if (checkedId == R.id.distaMax_2) {
                    ap.setSettingMaxDistance(50);
                } else {
                    ap.setSettingMaxDistance(Double.MAX_VALUE);
                }
                return;
            }
        });
        return root;
    }
}