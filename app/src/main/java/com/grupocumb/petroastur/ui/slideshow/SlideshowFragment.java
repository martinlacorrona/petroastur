package com.grupocumb.petroastur.ui.slideshow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.grupocumb.petroastur.ui.home.EstacionServicioAdapter;
import com.grupocumb.petroastur.ui.home.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SlideshowFragment extends Fragment {

    //private SlideshowViewModel slideshowViewModel;
    private List<EstacionServicio> es = new ArrayList<>();
    private RecyclerView recyclerView;
    private EstacionServicioAdapter mAdapter;

    private Double precioLimiteHastaVerde;
    private Double precioLimiteHastaAmarillo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel =
//                ViewModelProviders.of(getActivity()).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerFav);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        es = ((MainActivity) getActivity()).getAppController().getFavouritesOrdered();

        FuelType favorito = ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel();
        Double precioMaximo = es.stream().parallel()
                .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                .max(Comparator.comparingDouble(estacionServicio -> estacionServicio
                        .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);
        Double precioMinimo = es.stream().parallel()
                .filter(estacionServicio -> estacionServicio.getPrecioCombustible(favorito) > 0)
                .min(Comparator.comparingDouble(estacionServicio -> estacionServicio
                        .getPrecioCombustible(favorito))).get().getPrecioCombustible(favorito);

        Double diferenciaMaximoMinimo = precioMaximo - precioMinimo;
        Double diferenciaEnTresPartes = diferenciaMaximoMinimo / 3;
        precioLimiteHastaVerde = precioMinimo + diferenciaEnTresPartes * 1;
        precioLimiteHastaAmarillo = precioMinimo + diferenciaEnTresPartes * 2;

        mAdapter = new EstacionServicioAdapter(((MainActivity) getActivity()).getAppController().getFavouritesOrdered(),
                ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel(),
                (MainActivity) getActivity(), precioLimiteHastaVerde, precioLimiteHastaAmarillo);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DetalladaFragment fr = new DetalladaFragment(es.get(position));
                getActivity().getSupportFragmentManager().beginTransaction()
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Borrar favorito");

                builder.setMessage("Esta es la lista de favoritos, ¿desea borrar el seleccionado?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //ok
                                ((MainActivity) getActivity()).getAppController().removeFavourite(es.get(position).getId());
                                es = ((MainActivity) getActivity()).getAppController().getFavouritesOrdered();
                                mAdapter = new EstacionServicioAdapter(((MainActivity) getActivity()).getAppController().getFavouritesOrdered(),
                                        ((MainActivity) getActivity()).getAppController().getSettingFavouriteFuel(),
                                        (MainActivity) getActivity(), precioLimiteHastaVerde, precioLimiteHastaAmarillo);
                                recyclerView.setAdapter(mAdapter);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        }));
        return root;
    }
}