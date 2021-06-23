package com.elpunto.app.ui.miperfil;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.elpunto.app.MainActivity;
import com.elpunto.app.R;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.common.SharedPreferencesManager;
import com.elpunto.app.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    FragmentPerfilBinding binding;
    String urlFoto = "http://192.168.0.15:9898/api/usuarios/foto/" + SharedPreferencesManager.getSomeIntValue((Constantes.PREF_ID));
    String nombres = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_NOMBRES);
    String apellidos = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_APELLIDOS);

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        Glide.with(getContext()).load(urlFoto).apply(RequestOptions.circleCropTransform()).into(binding.ivFotoPerfil);
        binding.tvNombres.setText("Nombres: "+nombres);
        binding.tvApellidos.setText("Apellidos: "+apellidos);
        binding.btnEliminarUsuario.setOnClickListener(v -> {
            mostrarDialogoEliminarCuenta();
        });
        return vista;
    }

    public void mostrarDialogoEliminarCuenta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Eliminar cuenta ):");
        builder.setMessage("Estás seguro de eliminar tu cuenta? Después no podrás volver a usarla...")
                .setPositiveButton("Sí, estoy seguro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Aun no elimina el usuario, solo cierra la app...
                        getActivity().finishAffinity();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}