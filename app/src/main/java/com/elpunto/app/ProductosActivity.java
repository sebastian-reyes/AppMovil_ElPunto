package com.elpunto.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elpunto.app.databinding.ActivityProductosBinding;

public class ProductosActivity extends AppCompatActivity {
    ActivityProductosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer id_prod = getIntent().getExtras().getInt("id");
        String nombre_cat = getIntent().getExtras().getString("nombre");
        binding = ActivityProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvNombreCat.setText(nombre_cat.toString());
    }

}