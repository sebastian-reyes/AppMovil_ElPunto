package com.elpunto.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.elpunto.app.MainActivity;
import com.elpunto.app.ProductosActivity;
import com.elpunto.app.R;
import com.elpunto.app.databinding.ItemCategoriaBinding;
import com.elpunto.app.model.Categoria;
import com.elpunto.app.ui.categorias.CategoriasFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private ArrayList<Categoria> dataCategoria;
    private Context context;

    public CategoriaAdapter(Context context) {
        dataCategoria = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCategoriaBinding recyclerBinding = ItemCategoriaBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final Categoria objCat = dataCategoria.get(position);
        holder.binding.tvNombreCategoria.setText(objCat.getNombre_cat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productoIntent = new Intent(context, ProductosActivity.class);
                productoIntent.putExtra("id",objCat.getId_cat());
                productoIntent.putExtra("nombre",objCat.getNombre_cat());
                v.getContext().startActivity(productoIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCategoria.size();
    }

    public void agregarCategorias(ArrayList<Categoria> lstCategorias) {
        dataCategoria.addAll(lstCategorias);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriaBinding binding;

        public ViewHolder(@NonNull ItemCategoriaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
