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

import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.ui.slideshow.SlideshowViewModel;

import java.util.Set;

public class DetalladaFragment extends Fragment {

    private DetalladaViewModel homeViewModel;
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
        homeViewModel =
                ViewModelProviders.of(getActivity()).get(DetalladaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detallada, container, false);
//        final TextView textView = root.findViewById(R.id.distancia);
        nombre=(TextView)root.findViewById(R.id.nombreEstacion);
        direccion=(TextView)root.findViewById(R.id.direccion);
        carburantes=(TextView)root.findViewById(R.id.carburantes);
        favorito=(Button)root.findViewById(R.id.añadirFavoritas);
        mostrarMapa=(Button)root.findViewById(R.id.mostrarMapaButton);


        homeViewModel.getText().observe(this, new Observer<EstacionServicio>() {
            @Override
            public void onChanged(@Nullable EstacionServicio s) {
                nombre.setText(s.getId());
                direccion.setText(s.getDireccion());
                String hola="";
                hola+="-Biodiesel: "+s.getPrecioBiodiesel()+"€"+"\n";
                hola+="-Bioetanol: "+s.getPrecioBioetanol()+"€"+"\n";
                hola+="-Gas natural comprimido: "+s.getPrecioGasNaturalComprimido()+"€"+"\n";
                hola+="-Gas natural licuado: "+s.getPrecioGasNaturalLicuado()+"€"+"\n";
                hola+="-Gases licuados petroleo: "+s.getPrecioGasesLicuadosDelPetroleo()+"€"+"\n";
                hola+="-Gasoleo A: "+s.getPrecioGasoleoA()+"€"+"\n";
                hola+="-Gasoleo B: "+s.getPrecioGasoleoB()+"€"+"\n";
                hola+="-Gasolina 95: "+s.getPrecioGasolina95Proteccion()+"€"+"\n";
                hola+="-Gasolina 98: "+s.getPrecioGasolina98()+"€"+"\n";
                hola+="-Nuevo Gasoleo A: "+s.getPrecioNuevoGasoleoA()+"€"+"\n";
                carburantes.setText(hola);

            }
        });
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                Set<String> set2=preferences.getStringSet("favoritos",null);
                boolean se_encuentra=false;
                if (set2!=null){
                    for (String i: set2){
                        if (e.getId()==i){
                            se_encuentra=true;
                        }
                    }

                    if (!se_encuentra){
                        set2.add(e.getId());
                        editor.remove("favoritos");
                        editor.commit();
                        editor.putStringSet("favoritos", set2);
                        editor.commit();
                        slideshowViewModel =
                                ViewModelProviders.of(getActivity()).get(SlideshowViewModel.class);
                        slideshowViewModel.setmText(set2);
                    }
                }

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
}