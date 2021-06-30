package com.elpunto.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elpunto.app.databinding.ActivityFotoPerfilBinding;

public class FotoPerfilActivity extends AppCompatActivity {
    ActivityFotoPerfilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFotoPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}