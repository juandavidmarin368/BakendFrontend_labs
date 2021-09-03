package com.docker.PersisterDataDocker.App;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ItemsPaginados{


    String totalItems;
    String totalPages;
    List<HashMap<String, String>> items;

    public String getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<HashMap<String,String>> getItems() {
        return this.items;
    }

    public void setItems(List<HashMap<String,String>> items) {
        this.items = items;
    }

    public ItemsPaginados(String totalItems, String totalPages, List<HashMap<String,String>> items) {
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.items = items;
    }



}