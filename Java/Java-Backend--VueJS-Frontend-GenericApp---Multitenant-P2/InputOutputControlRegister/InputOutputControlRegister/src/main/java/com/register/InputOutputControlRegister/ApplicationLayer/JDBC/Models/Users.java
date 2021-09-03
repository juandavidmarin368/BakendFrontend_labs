package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;

public class Users{


    String fkIdPerfil;
    String fkIdCargo;
    String cedula;
    String nombre;
    String huella;

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

    public String getHuella() {
        return this.huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }

    public Users(String fkIdPerfil, String fkIdCargo, String cedula, String nombre, String huella) {
        this.fkIdPerfil = fkIdPerfil;
        this.fkIdCargo = fkIdCargo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.huella = huella;
    }


}