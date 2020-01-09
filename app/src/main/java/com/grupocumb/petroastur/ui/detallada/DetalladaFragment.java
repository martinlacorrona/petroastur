package com.grupocumb.petroastur.ui.detallada;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.ui.slideshow.SlideshowViewModel;

import java.util.List;
import java.util.Set;

public class DetalladaFragment extends Fragment {

    //private DetalladaViewModel homeViewModel;
    private EstacionServicio e;
    private TextView nombre;
    private TextView direccion;
    private TextView carburantes;
    private Button favorito;
    private Button mostrarMapa;
    private SlideshowViewModel slideshowViewModel;

    public DetalladaFragment(){

    }
    public DetalladaFragment(EstacionServicio e){
        this.e=e;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(getActivity()).get(DetalladaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detallada, container, false);
//        final TextView textView = root.findViewById(R.id.distancia);
        nombre=(TextView)root.findViewById(R.id.nombreEstacion);
        direccion=(TextView)root.findViewById(R.id.direccion);
        carburantes=(TextView)root.findViewById(R.id.carburantes);
        favorito=(Button)root.findViewById(R.id.añadirFavoritas);
        mostrarMapa=(Button)root.findViewById(R.id.mostrarMapaButton);

        nombre.setText(e.getId());
        direccion.setText(e.getDireccion());
        String hola="";
        hola+="-Biodiesel: "+e.getPrecioBiodiesel()+"€"+"\n";
        hola+="-Bioetanol: "+e.getPrecioBioetanol()+"€"+"\n";
        hola+="-Gas natural comprimido: "+e.getPrecioGasNaturalComprimido()+"€"+"\n";
        hola+="-Gas natural licuado: "+e.getPrecioGasNaturalLicuado()+"€"+"\n";
        hola+="-Gases licuados petroleo: "+e.getPrecioGasesLicuadosDelPetroleo()+"€"+"\n";
        hola+="-Gasoleo A: "+e.getPrecioGasoleoA()+"€"+"\n";
        hola+="-Gasoleo B: "+e.getPrecioGasoleoB()+"€"+"\n";
        hola+="-Gasolina 95: "+e.getPrecioGasolina95Proteccion()+"€"+"\n";
        hola+="-Gasolina 98: "+e.getPrecioGasolina98()+"€"+"\n";
        hola+="-Nuevo Gasoleo A: "+e.getPrecioNuevoGasoleoA()+"€"+"\n";
        carburantes.setText(hola);


//        homeViewModel.getText().observe(this, new Observer<EstacionServicio>() {
//            @Override
//            public void onChanged(@Nullable EstacionServicio s) {
//
//
//            }
//        });

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * MIIIIIIRAAAAAAAAAAAAAAAAAAAAAAAAAAAR
                * */
                List<EstacionServicio> favoritos=((MainActivity)getActivity()).getAppController().getFavouritesOrdered();
                for (EstacionServicio e:favoritos){
                    if (e.getId()==e.getId()){
                        Toast.makeText(
                                getActivity(),
                                "Ya se encuentra en favoritos.",
                                Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                }
                ((MainActivity)getActivity()).getAppController().addFavourite((e.getId()));

                /*
                * LISTA DE FAVORITOS
                * */
//
//                        slideshowViewModel =
//                                ViewModelProviders.of(getActivity()).get(SlideshowViewModel.class);
//                        slideshowViewModel.setmText(set2);


                Toast.makeText(
                        getActivity(),
                        "Se ha añadido a favoritos.",
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        mostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalladaFragment fr=new DetalladaFragment(e);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}