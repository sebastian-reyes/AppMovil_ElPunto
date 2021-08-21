package com.elpunto.app.common;

public class Constantes {

    //Con cable
    public static final String URL_BASE = "http://192.168.0.15:9898";

    //Con wifi
    //public static final String URL_BASE = "http://192.168.0.19:9898";

    public static final String URL_BASE_API =URL_BASE+"/api";
    public static final String URL_LOGIN = URL_BASE+"/api/usuarios/login";
    public static final String URL_REGISTRO = URL_BASE+"/api/usuarios/registro";
    public static final String URL_BASE_CATEGORIAS = URL_BASE+"/api/categorias/";
    public static final String URL_FOTO_PRODUCTO = URL_BASE+"/api/productos/foto/";

    //SharedPreferences
    public static final String PREF_ID = "PREF_ID";
    public static final String PREF_NOMBRES = "PREF_NOMBRES";
    public static final String PREF_APELLIDOS = "PREF_APELLIDOS";
    public static final String PREF_EMAIL = "PREF_EMAIL";
    public static final String PREF_TELEFONO = "PREF_TELEFONO";

    //Imagen
    public static final String CROPPED_IMG_NAME = "ImgCrop";
}
