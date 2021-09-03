package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;

public class Usuarios{

    public String id;
    public String fkIdPerfil;
    public String fkIdCargo;
    public String cedula;
    public String nombre;
    public String celular;
    public String email;
    public String activo;
    public String huella;
    public byte[] huella2;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFkIdPerfil() {
        return this.fkIdPerfil;
    }

    public void setFkIdPerfil(String fkIdPerfil) {
        this.fkIdPerfil = fkIdPerfil;
    }

    public String getFkIdCargo() {
        return this.fkIdCargo;
    }

    public void setFkIdCargo(String fkIdCargo) {
        this.fkIdCargo = fkIdCargo;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getHuella() {
        return this.huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }


    public Usuarios(String fkIdPerfil, String fkIdCargo, String cedula, String nombre, String huella) {
        this.fkIdPerfil = fkIdPerfil;
        this.fkIdCargo = fkIdCargo;
        this.cedula = cedula;
        this.nombre = nombre;
       this.huella = huella;
    }

    
    public Usuarios(String cedula, String nombre, byte[] huella2, String huella) {
       
        this.cedula = cedula;
        this.nombre = nombre;
        this.huella2 = huella2;
        this.huella = huella;
    }

}