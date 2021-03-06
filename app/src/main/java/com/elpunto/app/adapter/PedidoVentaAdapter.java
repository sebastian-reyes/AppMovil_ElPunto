package com.elpunto.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elpunto.app.R;
import com.elpunto.app.databinding.ItemPedidoBinding;
import com.elpunto.app.model.PedidoVenta;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PedidoVentaAdapter extends RecyclerView.Adapter<PedidoVentaAdapter.ViewHolder> {


    private ArrayList<PedidoVenta> dataPedidoVenta;
    private Context context;
    DecimalFormat formatDecimal = new DecimalFormat("####.00");

    public PedidoVentaAdapter(Context context) {
        dataPedidoVenta = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public PedidoVentaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPedidoBinding recyclerBinding = ItemPedidoBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidoVentaAdapter.ViewHolder holder, int position) {
        final PedidoVenta objPedido = dataPedidoVenta.get(position);

        if (objPedido.getEstado().toLowerCase().equals("en entrega")) {
            holder.binding.tvEstado.setTextColor(Color.parseColor("#ECE01F"));
        } else if (objPedido.getEstado().toLowerCase().equals("entregado")) {
            holder.binding.tvEstado.setTextColor(Color.parseColor("#4A8512"));
        } else{
            holder.binding.tvEstado.setTextColor(Color.parseColor("#CD1616"));
            holder.binding.tvTotal.setTextColor(Color.parseColor("#959B8F"));
        }
        holder.binding.tvID.setText("N° de Pedido: " + objPedido.getId_pdovta());
        holder.binding.tvEstado.setText(objPedido.getEstado());
        holder.binding.tvFecha.setText("Fecha: " + objPedido.getFecha());
        holder.binding.tvTotal.setText("S/. " +formatDecimal.format(objPedido.getTotal()));
    }

    @Override
    public int getItemCount() {
        return dataPedidoVenta.size();
    }

    public void agregarPedidos(ArrayList<PedidoVenta> lstpedidos) {
        dataPedidoVenta.addAll(lstpedidos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPedidoBinding binding;

        public ViewHolder(@NonNull @NotNull ItemPedidoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
