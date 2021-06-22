package com.elpunto.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.elpunto.app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnIngresar.setOnClickListener(v -> {
            if (binding.etEmail.getText().toString().equals("") &&
                    binding.etPassword.getText().toString().equals("")) {
                mostrarMensajeError("Los campos correo y contraseña no pueden estar vacíos");
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void mostrarMensajeError(String respuesta) {
        Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();
    }
}