package com.root.Generic.AplicationLayer.JDBC.Models;

import java.util.HashMap;
import java.util.List;

public class Totalventasdiarias {


    public int totalItems;

    public int getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String totalventas;

    List<HashMap<String, String>> Options;

    public String getTotalventas() {
        return this.totalventas;
    }

    public void setTotalventas(String totalventas) {
        this.totalventas = totalventas;
    }

    public List<HashMap<String,String>> getOptions() {
        return this.Options;
    }

    public void setOptions(List<HashMap<String,String>> Options) {
        this.Options = Options;
    }

    public Totalventasdiarias(String totalventas, List<HashMap<String,String>> Options, int totalItems) {
        this.totalventas = totalventas;
        this.Options = Options;
        this.totalItems = totalItems;
    }


  /* public String nombreProducto;

   public String cantidadProducto;
   
   public String valorVenta;

   public String fechayHora;

    public Totalventasdiarias(String nombreProducto, String cantidadProducto, String valorVenta, String fechayHora) {
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.valorVenta = valorVenta;
        this.fechayHora = fechayHora;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCantidadProducto() {
        return this.cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getValorVenta() {
        return this.valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getFechayHora() {
        return this.fechayHora;
    }

    public void setFechayHora(String fechayHora) {
        this.fechayHora = fechayHora;
    }
   */


}