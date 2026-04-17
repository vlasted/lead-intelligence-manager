package com.leadmanager.model;

import java.time.LocalDateTime;

public class Lead {

    private int idLead;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String cargo;
    private String interes;
    private int puntuacion;
    private String prioridad;
    private LocalDateTime fechaRegistro;
    private String observaciones;
    private int idEmpresa;
    private int idFuente;
    private int idEstado;
    private int idUsuarioResponsable;

    public Lead() {
    }

    public Lead(int idLead, String nombre, String apellidos, String email, String telefono,
                String cargo, String interes, int puntuacion, String prioridad,
                LocalDateTime fechaRegistro, String observaciones, int idEmpresa,
                int idFuente, int idEstado, int idUsuarioResponsable) {
        this.idLead = idLead;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.cargo = cargo;
        this.interes = interes;
        this.puntuacion = puntuacion;
        this.prioridad = prioridad;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
        this.idEmpresa = idEmpresa;
        this.idFuente = idFuente;
        this.idEstado = idEstado;
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    public int getIdLead() {
        return idLead;
    }

    public void setIdLead(int idLead) {
        this.idLead = idLead;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(int idFuente) {
        this.idFuente = idFuente;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(int idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "idLead=" + idLead +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", puntuacion=" + puntuacion +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}