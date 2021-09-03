package com.root.Generic.AplicationLayer.JDBC.Models;

public class Laboratorios{


    public int id;
    public String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laboratorios(int id, String name) {
        this.id = id;
        this.name = name;
    }

}