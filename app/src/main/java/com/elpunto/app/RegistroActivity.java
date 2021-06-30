package com.elpunto.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.databinding.ActivityRegistroBinding;
import com.elpunto.app.model.Rol;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    ActivityRegistroBinding binding;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegistrarse.setOnClickListener(v -> {
            try {
                Registro();
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void mostrarMensajeError(String respuesta) {
        Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();
    }

    public void Registro() throws JSONException {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("email", binding.etEmail.getText().toString());
        parametros.put("password", binding.etPasswordRg.getText().toString());
        parametros.put("dni", binding.etDni.getText().toString());
        parametros.put("nombres", binding.etNombres.getText().toString());
        parametros.put("apellidos", binding.etApellidos.getText().toString());
        parametros.put("telefono", binding.etTelefono.getText().toString());
        parametros.put("foto", null);
        JSONObject objRol = new JSONObject(gson.toJson(new Rol(2)));
        parametros.put("rol", objRol);
        JSONObject jsonObject = new JSONObject(parametros);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_REGISTRO,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(new Intent(RegistroActivity.this, FotoPerfilActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        String object = new String(String.valueOf(jsonObject));
                        String jsonResponse = new String(networkResponse.data);
                        try {
                            JSONObject jsonError = new JSONObject(jsonResponse);
                            String msj = jsonError.getString("mensaje");
                            mostrarMensajeError(msj);
                            System.out.println(object);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(object);
                    }
                }
        );
        colaPeticiones.add(request);
    }
}