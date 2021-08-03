package com.elpunto.app.ui.categorias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elpunto.app.MainActivity;
import com.elpunto.app.R;
import com.elpunto.app.adapter.CategoriaAdapter;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.databinding.FragmentCategoriasBinding;
import com.elpunto.app.model.Categoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class    CategoriasFragment extends Fragment{
    private CategoriaAdapter adapter;
    private FragmentCategoriasBinding binding;

    public CategoriasFragment() {
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
        binding = FragmentCategoriasBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        adapter = new CategoriaAdapter(getContext());
        binding.rvCategorias.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCategorias.setAdapter(adapter);
        obtenerCategorias(Constantes.URL_BASE_API + "/categorias");
        return vista;
    }

    private void obtenerCategorias(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categorias");
                            ArrayList<Categoria> listaCategorias = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Categoria c = new Categoria(
                                        jsonObject.getInt("id_cat"),
                                        jsonObject.getString("nombre_cat")
                                );
                                listaCategorias.add(c);
                            }
                            adapter.agregarCategorias(listaCategorias);
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