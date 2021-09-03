package com.root.Generic.AplicationLayer.JDBC.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotacionProductosFinal {


    public String nombreProducto;
    public String idProducto;

    List<HashMap<String, String>> Options;

    public int totalElements;
    public int totalPages;

    public int currentPage;

    public int cantidadActual;

    public int getCantidadActual() {
        return this.cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public List<HashMap<String,String>> getOptions() {
        return this.Options;
    }

    public void setOptions(List<HashMap<String,String>> Options) {
        this.Options = Options;
    }

    public RotacionProductosFinal(String nombreProducto, String idProducto, List<HashMap<String,String>> Options, int totalElements, int totalPages, int currentPage, int cantidadActual) {
        this.nombreProducto = nombreProducto;
        this.idProducto = idProducto;
        this.Options = Options;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.cantidadActual = cantidadActual;

    }

    
}