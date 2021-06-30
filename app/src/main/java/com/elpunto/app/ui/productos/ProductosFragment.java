package com.elpunto.app.ui.productos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpunto.app.R;
import com.elpunto.app.databinding.FragmentProductosBinding;

public class ProductosFragment extends Fragment {
    FragmentProductosBinding binding;
    String nombreProd;

    public ProductosFragment(String nombre) {
        // Required empty public constructor
        nombreProd = nombre;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductosBinding.inflate(inflater,container,false);
        View vista = binding.getRoot();
        binding.aea.setText(nombreProd);
        return vista;
    }
}