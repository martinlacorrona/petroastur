package com.grupocumb.petroastur.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public class EstacionServicioAdapter extends RecyclerView.Adapter<EstacionServicioAdapter.MyViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    private final MainActivity m;

    List<EstacionServicio> EstacionList;
    //Componentes de la vista. Clase interna que los define
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre,direccion, distancia,precio;

        public MyViewHolder(View view) {
            super(view);
            nombre =  view.findViewById(R.id.nombre);
            direccion =  view.findViewById(R.id.direccion);
            distancia =  view.findViewById(R.id.distancia);
            precio =  view.findViewById(R.id.precio);

        }
    }
    public EstacionServicioAdapter(List<EstacionServicio> e, MainActivity m){
        this.EstacionList=e;
        this.m=m;
    }
    @NonNull
    @Override
    //Creacion de la vista de cada item
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //instancia el recview.xml en un vista
        //si no ponemos false al attachRoot, casca y no dicen por qué
        View itemView;
            itemView= LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.recview,parent,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (EstacionList != null && EstacionList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }


    @Override
    //Asociación de contenido a los componentes de la vista
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EstacionServicio estacion = EstacionList.get(position);
        holder.nombre.setText(estacion.getId());
        holder.direccion.setText(estacion.getDireccion());

        //holder.distancia.setText(estacion.);
        //holder.titulo.setText(estacion.getTitulo());
        //holder.anio.setText(video.getAnio());
    }

    @Override
    public int getItemCount() {
        return EstacionList.size();
    }

}
