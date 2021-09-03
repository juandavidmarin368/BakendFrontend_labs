package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;


public class Cargo{

    public String id;
    public String fkIdPerfilCargo;
    public String cargoNombre;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFkIdPerfilCargo() {
        return this.fkIdPerfilCargo;
    }

    public void setFkIdPerfilCargo(String fkIdPerfilCargo) {
        this.fkIdPerfilCargo = fkIdPerfilCargo;
    }

    public String getCargoNombre() {
        return this.cargoNombre;
    }

    public void setCargoNombre(String cargoNombre) {
        this.cargoNombre = cargoNombre;
    }

    public Cargo(String fkIdPerfilCargo, String cargoNombre) {
     
        this.fkIdPerfilCargo = fkIdPerfilCargo;
        this.cargoNombre = cargoNombre;
    }




}