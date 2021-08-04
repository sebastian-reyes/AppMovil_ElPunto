package com.elpunto.app.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.databinding.ItemProductoBinding;
import com.elpunto.app.model.Producto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {
    private ArrayList<Producto> dataProducto;
    private Context context;
    private ArrayList<Producto> listaProdOriginal;

    public ProductoAdapter(Context context) {
        this.dataProducto = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductoBinding recyclerBinding = ItemProductoBinding.inflate(layoutInflater, parent,
                false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final Producto objProducto = dataProducto.get(position);
        holder.binding.tvNombreProd.setText(objProducto.getNombre());
        holder.binding.tvPrecioProd.setText("S/." + String.valueOf(objProducto.getPrecio()));
        Glide.with(context).load(Constantes.URL_FOTO_PRODUCTO + objProducto.getId_producto().toString())
                .into(holder.binding.ivProducto);
    }

    @Override
    public int getItemCount() {
        return dataProducto.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filtroProductos(String cadenaBusqueda) {
        int longitud = cadenaBusqueda.length();
        if (longitud == 0) {
            dataProducto.clear();
            dataProducto.addAll(listaProdOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Producto> coleccion = dataProducto.stream().filter(i -> i.getNombre().toLowerCase()
                        .contains(cadenaBusqueda.toLowerCase())).collect(Collectors.toList());
                dataProducto.clear();
                dataProducto.addAll(coleccion);
            } else {
                for (Producto p : listaProdOriginal) {
                    if (p.getNombre().toLowerCase().contains(cadenaBusqueda.toLowerCase())) {
                        dataProducto.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void agregarProductos(ArrayList<Producto> lstproductos) {
        dataProducto.addAll(lstproductos);
        listaProdOriginal = new ArrayList<>();
        listaProdOriginal.addAll(dataProducto);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding binding;

        public ViewHolder(@NonNull @NotNull ItemProductoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
