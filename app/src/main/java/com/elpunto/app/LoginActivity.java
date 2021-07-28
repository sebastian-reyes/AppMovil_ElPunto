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
import com.elpunto.app.common.SharedPreferencesManager;
import com.elpunto.app.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegistro.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
        });
        binding.btnIngresar.setOnClickListener(v -> {
            if (binding.etEmail.getText().toString().equals("") ||
                    binding.etPassword.getText().toString().equals("")) {
                mostrarMensajeError("Los campos correo y contraseña no pueden estar vacíos");
            } else {
                Login();
                finish();
            }
        });
        validarDatos();
    }

    private void mostrarMensajeError(String respuesta) {
        Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();
    }

    private void validarDatos() {
        if (!SharedPreferencesManager.getSomeStringValue(Constantes.PREF_NOMBRES).equals("")) {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    public void Login() {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);
        Map<String, String> parametros = new HashMap<>();
        parametros.put("email", binding.etEmail.getText().toString());
        parametros.put("password", binding.etPassword.getText().toString());
        JSONObject jsonObjectParametro = new JSONObject(parametros);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_LOGIN,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("id_usuario") > 0) {
                                SharedPreferencesManager.setSomeIntValue(
                                        Constantes.PREF_ID,
                                        response.getInt("id_usuario")
                                );
                                SharedPreferencesManager.setSomeStringValue(
                                        Constantes.PREF_NOMBRES,
                                        response.getString("nombres")
                                );
                                SharedPreferencesManager.setSomeStringValue(
                                        Constantes.PREF_APELLIDOS,
                                        response.getString("apellidos")
                                );
                                SharedPreferencesManager.setSomeStringValue(
                                        Constantes.PREF_EMAIL,
                                        response.getString("email")
                                );
                                SharedPreferencesManager.setSomeStringValue(
                                        Constantes.PREF_TELEFONO,
                                        response.getString("telefono")
                                );
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                mostrarMensajeError(response.getString("mensaje"));
                            }
                        } catch (JSONException ex) {
                            mostrarMensajeError("Error en el servidor, inténtelo de nuevo.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        String jsonResponse = new String(networkResponse.data);
                        try {
                            JSONObject jsonError = new JSONObject(jsonResponse);
                            String msj = jsonError.getString("mensaje");
                            mostrarMensajeError(msj);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        colaPeticiones.add(request);
    }
}