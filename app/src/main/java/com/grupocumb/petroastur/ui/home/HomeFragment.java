package com.grupocumb.petroastur.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;
import com.grupocumb.petroastur.ui.detallada.DetalladaFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<EstacionServicio> listaAllEESSOrdered = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        listaAllEESSOrdered = ((MainActivity) getActivity()).getAppController().getAllEESSOrdered();

        //Para que depende del color saque uno o otro.
        FuelType favorito = ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel();
        Double precioMaximo = listaAllEESSOrdered.stream().parallel()
                .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                .max(Comparator.comparingDouble(estacionServicio -> estacionServicio
                        .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);
        Double precioMinimo = listaAllEESSOrdered.stream().parallel()
                .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                .min(Comparator.comparingDouble(estacionServicio -> estacionServicio
                        .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);

        Double diferenciaMaximoMinimo = precioMaximo - precioMinimo;
        Double diferenciaEnTresPartes = diferenciaMaximoMinimo / 3;
        Double precioLimiteHastaVerde = precioMinimo + diferenciaEnTresPartes * 1;
        Double precioLimiteHastaAmarillo = precioMinimo + diferenciaEnTresPartes * 2;

        EstacionServicioAdapter mAdapter = new EstacionServicioAdapter(((MainActivity) getActivity()).getAppController().getAllEESSOrdered(),
                ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel(),
                (MainActivity) getActivity(), precioLimiteHastaVerde, precioLimiteHastaAmarillo);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DetalladaFragment fr = new DetalladaFragment(listaAllEESSOrdered.get(position));
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_o, fr)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onLongClick(View view, final int position) {
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
            }
        }));
        return root;
    }
}