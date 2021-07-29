package com.elpunto.app.ui.pedidos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elpunto.app.R;
import com.elpunto.app.adapter.PedidoVentaAdapter;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.common.SharedPreferencesManager;
import com.elpunto.app.databinding.FragmentPedidosBinding;
import com.elpunto.app.model.PedidoVenta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PedidosFragment extends Fragment {
    private PedidoVentaAdapter adapter;
    private FragmentPedidosBinding binding;

    public PedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPedidosBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        adapter = new PedidoVentaAdapter(getContext());
        binding.rvPedidos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPedidos.setAdapter(adapter);
        obtenerPedidos(Constantes.URL_BASE_API + "/usuarios/" + SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID));
        return vista;
    }

    private void obtenerPedidos(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("pedidos");
                            ArrayList<PedidoVenta> listaPedidos = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PedidoVenta pedidoVenta = new PedidoVenta(
                                        jsonObject.getInt("id_pdovta"),
                                        jsonObject.getString("tipo_pago"),
                                        jsonObject.getString("fecha"),
                                        jsonObject.getString("estado"),
                                        jsonObject.getBoolean("cancelado"),
                                        jsonObject.getString("direccion"),
                                        jsonObject.getDouble("total")
                                );
                                listaPedidos.add(pedidoVenta);
                            }
                            adapter.agregarPedidos(listaPedidos);
                        } catch (JSONException ex) {

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
}