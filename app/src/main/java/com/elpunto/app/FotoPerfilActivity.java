package com.elpunto.app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elpunto.app.common.Constantes;
import com.elpunto.app.common.VolleyExtends.MultipartRequest;
import com.elpunto.app.databinding.ActivityFotoPerfilBinding;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FotoPerfilActivity extends AppCompatActivity {
    ActivityFotoPerfilBinding binding;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer id = getIntent().getExtras().getInt("id");
        binding = ActivityFotoPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityResultLauncher<Intent> intentLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri filePath = data.getData();
                        try {
                            //recortarImagen(filePath);
                            //Uri imagenRecortada = UCrop.getOutput(data);
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            binding.ivFotoPerfilRegistro.setImageBitmap(bitmap);
                            //binding.ivFotoPerfilRegistro.setImageURI(imagenRecortada);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        binding.btnElegirFoto.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            //Intent.createChooser(intent,"Seleccione una imagen");
            intentLaunch.launch(intent);
        });

        binding.btnSubirFoto.setOnClickListener(v -> {
            subirImagen(id);
        });

    }

    public byte[] getByteImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        //String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageBytes;
    }

    private void subirImagen(Integer id) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(this);
        MultipartRequest stringRequest = new MultipartRequest(
                Request.Method.POST,
                Constantes.URL_BASE_API + "/usuarios/upload",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        finish();
                        startActivity(new Intent(FotoPerfilActivity.this, LoginActivity.class));
                        Toast.makeText(FotoPerfilActivity.this,"Usuario registrado con éxito",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FotoPerfilActivity.this,"No se pudo subir la imagen",Toast.LENGTH_LONG).show();
                    }
                }
            ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id.toString());
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                params.put("foto", new DataPart(id+".jpg", getByteImagen(bitmap)));
                return params;
            }
        };
        colaPeticiones.add(stringRequest);
    }

    private void recortarImagen(Uri uri) {
        String destinationFileName = Constantes.CROPPED_IMG_NAME;
        destinationFileName += ".jpg";
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
        uCrop.withAspectRatio(1, 1);
        uCrop.withMaxResultSize(450, 450);
        uCrop.withOptions(obtenerOpciones());
        uCrop.start(FotoPerfilActivity.this);
    }

    private UCrop.Options obtenerOpciones() {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(70);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);
        options.setToolbarTitle("Recortar imagen");
        return options;
    }
}