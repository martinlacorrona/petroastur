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
import com.grupocumb.petroastur.ui.home.HomeViewModel;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private HomeViewModel homeViewModel;
    private RadioGroup radioButtonCombustPreferido;
    private RadioButton bioDiesel;
    private RadioButton bioetanol;
    private RadioButton gas_natural_comprimido;
    private RadioButton gas_natural_licuado;
    private RadioButton gases_licuados_petroleo;
    private RadioButton gasoleo_a;
    private RadioButton gasoleo_b;
    private RadioButton gasoleo95;
    private RadioButton gasoleo1_98;
    private RadioButton nuevo_gasoleo_a;

    private RadioButton ordenDista;
    private RadioButton ordenPrecio;

    private RadioButton distancia1;
    private RadioButton distancia2;


    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        radioButtonCombustPreferido = (RadioGroup) root.findViewById(R.id.grupoCarburante);
        bioDiesel = (RadioButton) root.findViewById(R.id.dieselRadioButton);
        bioetanol = (RadioButton) root.findViewById(R.id.bioEtanolButton);
        gas_natural_comprimido = (RadioButton) root.findViewById(R.id.gasNaturalComprimidoButton);
        gas_natural_licuado = (RadioButton) root.findViewById(R.id.gasNaturalLicuadoButton);
        gases_licuados_petroleo = (RadioButton) root.findViewById(R.id.gasesLicuadosDelPetroleoButton);
        gasoleo_a = (RadioButton) root.findViewById(R.id.gasoleoAButton);
        gasoleo_b = (RadioButton) root.findViewById(R.id.gasoleoBButton);
        gasoleo95 = (RadioButton) root.findViewById(R.id.gasolina95Button);
        gasoleo1_98 = (RadioButton) root.findViewById(R.id.gasolina98Button);
        nuevo_gasoleo_a = (RadioButton) root.findViewById(R.id.nuevoGasoleoA);

        ordenDista = (RadioButton) root.findViewById(R.id.distanc);
        ordenPrecio = (RadioButton) root.findViewById(R.id.preci);

        distancia1 = (RadioButton) root.findViewById(R.id.disMax1);
        distancia2 = (RadioButton) root.findViewById(R.id.distaMax_2);
        RadioButton r = (RadioButton) root.findViewById(R.id.sin_maxima);

        if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.BIODIESEL) {
            bioDiesel.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.BIOETANOL) {
            bioetanol.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GAS_NATURAL_COMPRIMIDO) {
            gas_natural_comprimido.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GAS_NATURAL_LICUADO
        ) {
            gas_natural_licuado.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASES_LICUADOS_PETROLEO) {
            gases_licuados_petroleo.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLEO_A) {
            gasoleo_a.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLEO_B) {
            gasoleo_b.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLINA_95) {
            gasoleo95.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.GASOLINA_98) {
            gasoleo1_98.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel() == FuelType.NUEVO_GASOLEO_A) {
            nuevo_gasoleo_a.setSelected(true);
        }

        if (((MainActivity) getActivity()).getAppController().getSettingOrder() == OrderType.DISTANCIA) {
            ordenDista.setSelected(true);
        } else {
            ordenPrecio.setSelected(true);
        }

        if (((MainActivity) getActivity()).getAppController().getSettingMaxDistance() == 20) {
            distancia1.setSelected(true);
        } else if (((MainActivity) getActivity()).getAppController().getSettingMaxDistance() == 50) {
            distancia2.setSelected(true);
        } else {

            r.setSelected(true);
        }

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();

        AppController ap = ((MainActivity) getActivity()).getAppController();
        //Mirar cual está seleccionado
        if (bioDiesel.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.BIODIESEL);
        } else if (bioetanol.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.BIOETANOL);
        } else if (gas_natural_comprimido.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GAS_NATURAL_COMPRIMIDO);
        } else if (gas_natural_licuado.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GAS_NATURAL_LICUADO);
        } else if (gases_licuados_petroleo.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GASES_LICUADOS_PETROLEO);
        } else if (gasoleo_a.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GASOLEO_A);
        } else if (gasoleo_b.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GASOLEO_B);
        } else if (gasoleo95.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GASOLINA_95);
        } else if (gasoleo1_98.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.GASOLINA_98);
        } else if (nuevo_gasoleo_a.isSelected()) {
            ap.setSettingFavouriteFuel(FuelType.NUEVO_GASOLEO_A);
        }

        if (ordenPrecio.isSelected()) {
            ap.setSettingOrder(OrderType.PRECIO);
        } else {
            ap.setSettingOrder(OrderType.DISTANCIA);
        }

        if (distancia1.isSelected()) {
            ap.setSettingMaxDistance(20);
        } else if (distancia2.isSelected()) {
            ap.setSettingMaxDistance(50);
        } else {
            ap.setSettingMaxDistance(Double.MAX_VALUE);
        }

    }
}