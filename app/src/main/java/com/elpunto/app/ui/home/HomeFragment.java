package com.elpunto.app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.elpunto.app.R;
import com.elpunto.app.adapter.CategoriaAdapter;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.common.SharedPreferencesManager;
import com.elpunto.app.databinding.FragmentCategoriasBinding;
import com.elpunto.app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        binding.tvHomeBienvenida.setText("Bienvenido "+ SharedPreferencesManager.getSomeStringValue("PREF_NOMBRES")+ " "+
                SharedPreferencesManager.getSomeStringValue("PREF_APELLIDOS"));
        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}