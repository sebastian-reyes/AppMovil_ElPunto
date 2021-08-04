package com.elpunto.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elpunto.app.adapter.ProductoAdapter;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.databinding.ActivityProductosBinding;
import com.elpunto.app.model.Producto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ActivityProductosBinding binding;
    private ProductoAdapter adapter;
    private SearchView svProd;

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
        obtenerProductos(Constantes.URL_BASE_CATEGORIAS+id_prod);
        adapter = new ProductoAdapter(ProductosActivity.this);
        binding.rvProductos.setAdapter(adapter);
        svProd = findViewById(R.id.svProductos);
        svProd.setOnQueryTextListener(this);
    }

    private void obtenerProductos(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("productos");
                            ArrayList<Producto> listaProductos = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Producto p = new Producto(
                                        jsonObject.getInt("id_producto"),
                                        jsonObject.getString("nombre"),
                                        jsonObject.getString("fecha_venc"),
                                        jsonObject.getString("desc_prod"),
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