package com.elpunto.app.model;

import java.util.Date;

public class Usuario {
    private Integer id_usuario;
    private Integer id_rol;
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private Date fecha_creacion;

    public Usuario(Integer id_usuario, Integer id_rol, String email, String password,
                   String nombres, String apellidos, String dni, String telefono,
                   Date fecha_creacion) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.fecha_creacion = fecha_creacion;
    }

    public Usuario() {
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
