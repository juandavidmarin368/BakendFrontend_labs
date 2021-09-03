package com.example.Pagination.JDBC.Models;

import java.util.HashMap;
import java.util.List;

public class FinalProducts {

    public String productName;
    public String productId;
    public String totalPages;
  
    List<HashMap<String, String>> details;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<HashMap<String,String>> getDetails() {
        return this.details;
    }

    public void setDetails(List<HashMap<String,String>> details) {
        this.details = details;
    }

    public FinalProducts(String productName, String productId, String totalPages, List<HashMap<String,String>> details) {
        this.productName = productName;
        this.productId = productId;
        this.totalPages = totalPages;
        this.details = details;
    }

   

}