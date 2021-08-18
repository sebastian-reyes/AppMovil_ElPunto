package com.elpunto.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.elpunto.app.adapter.ProductoAdapter;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.databinding.ActivityProductosBinding;
import com.elpunto.app.databinding.ItemProductoDetalleBinding;
import com.elpunto.app.model.Producto;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    DecimalFormat formatDecimal = new DecimalFormat("####.00");
    private ActivityProductosBinding binding;
    private ProductoAdapter adapter;
    private BottomSheetDialog bottomSheetDialog;
    private TextView tvNompreProdDialog, tvDescripcionProdDialog, tvPrecioProdDialog;
    private ImageView ivDetalleProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer id_prod = getIntent().getExtras().getInt("id");
        String nombre_cat = getIntent().getExtras().getString("nombre");
        binding = ActivityProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.itemProdVolver.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.tvNombreCat.setText(nombre_cat.toString());
        binding.rvProductos.setLayoutManager(
                new GridLayoutManager(ProductosActivity.this, 2));
        ArrayList<Producto> lstprod = obtenerProductos(Constantes.URL_BASE_CATEGORIAS + id_prod);
        adapter = new ProductoAdapter(ProductosActivity.this);
        binding.rvProductos.setAdapter(adapter);
        binding.svProductos.setOnQueryTextListener(this);
        adapter.onClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = binding.rvProductos.getChildAdapterPosition(v);
                //Toast.makeText(ProductosActivity.this,"posición "+pos,Toast.LENGTH_SHORT).show();
                mostrarDetalle(lstprod, pos);
            }
        });
    }

    private ArrayList<Producto> obtenerProductos(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("productos");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Producto p = new Producto(
                                        jsonObject.getInt("id_producto"),
                                        jsonObject.getString("nombre"),
                                        jsonObject.getString("desc_prod"),
                                        jsonObject.getString("fecha_venc"),
                                        jsonObject.getDouble("precio"),
                                        jsonObject.getInt("stock_min"),
                                        jsonObject.getInt("stock_act")
                                );
                                listaProductos.add(p);
                            }
                            adapter.agregarProductos(listaProductos);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        colaPeticiones.add(jsonObjectRequest);
        return listaProductos;
    }

    private void mostrarDetalle(ArrayList<Producto> lstp, int position) {
        bottomSheetDialog = new BottomSheetDialog(ProductosActivity.this);
        View view = getLayoutInflater().from(ProductosActivity.this).inflate(R.layout.item_producto_detalle, null);

        tvNompreProdDialog = view.findViewById(R.id.tvDNombreProducto);
        tvDescripcionProdDialog = view.findViewById(R.id.tvDDescripcionProd);
        tvPrecioProdDialog = view.findViewById(R.id.tvDPrecioProd);

        ivDetalleProducto = view.findViewById(R.id.ivDetProducto);
        if (position != -1) {
            int id = lstp.get(position).getId_producto();
            tvNompreProdDialog.setText(lstp.get(position).getNombre());
            tvDescripcionProdDialog.setText(lstp.get(position).getDesc_prod());
            tvPrecioProdDialog.setText(("S/." + formatDecimal.format(lstp.get(position).getPrecio())));
            Glide.with(ProductosActivity.this).load(Constantes.URL_FOTO_PRODUCTO + id)
                    .into(ivDetalleProducto);
        }

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtroProductos(newText);
        return false;
    }
}