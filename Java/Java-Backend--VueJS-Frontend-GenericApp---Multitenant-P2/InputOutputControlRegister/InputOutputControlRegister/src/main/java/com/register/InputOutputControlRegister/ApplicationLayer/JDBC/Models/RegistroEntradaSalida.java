package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;


public class RegistroEntradaSalida{

    public String cedula;
    public String tipoRegistro;
    public String fechaconhoraRegistro;
    public String fechaRegistro;

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getFechaconhoraRegistro() {
        return this.fechaconhoraRegistro;
    }

    public void setFechaconhoraRegistro(String fechaconhoraRegistro) {
        this.fechaconhoraRegistro = fechaconhoraRegistro;
    }

    public String getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public RegistroEntradaSalida(String cedula, String tipoRegistro, String fechaconhoraRegistro, String fechaRegistro) {
        this.cedula = cedula;
        this.tipoRegistro = tipoRegistro;
        this.fechaconhoraRegistro = fechaconhoraRegistro;
        this.fechaRegistro = fechaRegistro;
    }

 

}