package com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Model;

public class Database{

   
    public String url;
    public String username;
    public String password;
    public String tenantid;
    public String driver;

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTenantid() {
        return this.tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Database(String url, String username, String password, String tenantid, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.tenantid = tenantid;
        this.driver = driver;
    }


}