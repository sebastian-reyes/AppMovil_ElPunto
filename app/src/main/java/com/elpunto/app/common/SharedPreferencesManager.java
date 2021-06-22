package com.elpunto.app.common;

import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    public SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences() {
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, MyApp.MODE_PRIVATE);
    }
    public static void setSomeStringValue(String nombrePropiedad, String valor) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(nombrePropiedad, valor);
        editor.commit();
    }

    public static void setSomeIntValue(String nombrePropiedad, Integer valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(nombrePropiedad,valor);
        editor.commit();
    }

    public static String getSomeStringValue(String nombrePropiedad){
        return getSharedPreferences().getString(nombrePropiedad,"");
    }

    public static Integer getSomeIntValue(String nombrePropiedad){
        return getSharedPreferences().getInt(nombrePropiedad,0);
    }
}
