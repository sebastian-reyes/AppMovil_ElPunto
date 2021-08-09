package com.elpunto.app.model;

public class Producto {
    private Integer id_producto;
    private String nombre;
    private String fecha_venc;
    private String desc_prod;
    private Double precio;
    private Integer stock_act;
    private Integer stock_min;

    public Producto(Integer id_producto, String nombre, String desc_prod, String fecha_venc,
                    Double precio, Integer stock_act, Integer stock_min) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.desc_prod = desc_prod;
        this.fecha_venc = fecha_venc;
        this.precio = precio;
        this.stock_act = stock_act;
        this.stock_min = stock_min;
    }

    public Producto() {
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc_prod() {
        return desc_prod;
    }

    public void setDesc_prod(String desc_prod) {
        this.desc_prod = desc_prod;
    }

    public String getFecha_venc() {
        return fecha_venc;
    }

    public void setFecha_venc(String fecha_venc) {
        this.fecha_venc = fecha_venc;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock_act() {
        return stock_act;
    }

    public void setStock_act(Integer stock_act) {
        this.stock_act = stock_act;
    }

    public Integer getStock_min() {
        return stock_min;
    }

    public void setStock_min(Integer stock_min) {
        this.stock_min = stock_min;
    }
}
