package com.grupocumb.petroastur.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupocumb.petroastur.MainActivity;
import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.FuelType;

import java.util.List;

public class EstacionServicioAdapter extends RecyclerView.Adapter<EstacionServicioAdapter.MyViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private final double precioHastaVerde;
    private final double precioHastaAmarillo;

    private List<EstacionServicio> EstacionList;
    private FuelType ft;

    public EstacionServicioAdapter(List<EstacionServicio> e, FuelType ft, MainActivity m,
                                   double precioHastaVerde, double precioHastaAmarillo) {
        this.EstacionList = e;
        this.ft = ft;
        this.precioHastaVerde = precioHastaVerde;
        this.precioHastaAmarillo = precioHastaAmarillo;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, direccion, precio, combustible;
        public ImageView logoPrecio;

        public MyViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.nombre);
            direccion = view.findViewById(R.id.direccion);
            precio = view.findViewById(R.id.precio);
            logoPrecio = view.findViewById(R.id.imageprecio);
            combustible = view.findViewById(R.id.combustible);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recview, parent, false);
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EstacionServicio estacion = EstacionList.get(position);
        Double precioFavorito = estacion.getPrecioCombustible(ft);
        holder.nombre.setText(estacion.getEmpresa());
        holder.direccion.setText(estacion.getDireccion() +
                ", " +
                estacion.getLocalidad());
        if(estacion.getPrecioCombustible(ft) == 0.0)
            holder.precio.setText("No ofrece este tipo de combustible");
        else
            holder.precio.setText(estacion.getPrecioCombustible(ft).toString() + " €");
        holder.combustible.setText(ft.getFormattedName());

        if(precioFavorito == 0.0) { //si no tiene precio es que no esta disponible
            holder.logoPrecio.setImageResource(R.drawable.precioalto);
            holder.precio.setTextColor(Color.parseColor("#CB4335"));
        } else if (precioFavorito < precioHastaVerde) {
            holder.logoPrecio.setImageResource(R.drawable.preciobajo);
            holder.precio.setTextColor(Color.parseColor("#28B463"));
        } else if (precioFavorito >= precioHastaVerde && precioFavorito <= precioHastaAmarillo) {
            holder.logoPrecio.setImageResource(R.drawable.preciomedio);
            holder.precio.setTextColor(Color.parseColor("#F1C40F"));
        } else {
            holder.logoPrecio.setImageResource(R.drawable.precioalto);
            holder.precio.setTextColor(Color.parseColor("#CB4335"));
        }
    }

    @Override
    public int getItemCount() {
        return EstacionList.size();
    }

}
