package com.elpunto.app.model;

import java.util.Date;

public class PedidoVenta {
    private Integer id_pdovta;
    private String tipo_pago;
    private String fecha;
    private String estado;
    private Boolean cancelado;
    private String direccion;
    private Double total;

    public PedidoVenta(Integer id_pdovta, String tipo_pago, String fecha, String estado,
                       Boolean cancelado, String direccion, Double total) {
        this.id_pdovta = id_pdovta;
        this.tipo_pago = tipo_pago;
        this.fecha = fecha;
        this.estado = estado;
        this.cancelado = cancelado;
        this.direccion = direccion;
        this.total = total;
    }

    public PedidoVenta() {
    }

    public Integer getId_pdovta() {
        return id_pdovta;
    }

    public void setId_pdovta(Integer id_pdovta) {
        this.id_pdovta = id_pdovta;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
