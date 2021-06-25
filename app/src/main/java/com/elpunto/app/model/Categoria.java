package com.elpunto.app.model;

public class Categoria {
    private int id_cat;
    private String nombre_cat;

    public Categoria(int id_cat, String nombre_cat) {
        this.id_cat = id_cat;
        this.nombre_cat = nombre_cat;
    }

    public Categoria() {
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNombre_cat() {
        return nombre_cat;
    }

    public void setNombre_cat(String nombre_cat) {
        this.nombre_cat = nombre_cat;
    }
}
