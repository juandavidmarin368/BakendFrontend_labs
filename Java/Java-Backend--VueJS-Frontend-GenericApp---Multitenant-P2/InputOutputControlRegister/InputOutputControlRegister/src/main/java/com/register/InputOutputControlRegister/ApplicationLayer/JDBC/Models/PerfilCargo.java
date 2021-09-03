package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;

public class PerfilCargo{

    public String id;
    public String nombrePerfilCargo;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrePerfilCargo() {
        return this.nombrePerfilCargo;
    }

    public void setNombrePerfilCargo(String nombrePerfilCargo) {
        this.nombrePerfilCargo = nombrePerfilCargo;
    }

    public PerfilCargo(String id, String nombrePerfilCargo) {
        this.id = id;
        this.nombrePerfilCargo = nombrePerfilCargo;
    }

}