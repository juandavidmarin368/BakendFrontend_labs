package com.tenancy.MultitenantJPAandJdbcTemplate.MultiTenantSettings.TenantDataSources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JDBC.Dao.UserDAO;
import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JDBC.Model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DataSourceMap {
    

     public static String getData()throws ClientProtocolException, IOException{

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8090/dynamic/databases");
        HttpResponse response = (HttpResponse) client.execute(request);
        String result = EntityUtils.toString(response.getEntity());

            
        return result;


     }



    public static Map<Object, Object> getDataSourceHashMap()  {


        HashMap hashMap = new HashMap();

        try{
            String result = getData();

            // CONVERT RESPONSE STRING TO JSON ARRAY
            JSONArray ja = new JSONArray(result);

            // ITERATE THROUGH AND RETRIEVE CLUB FIELDS
            int n = ja.length();
            
            int temporal = 1;
            
            for (int i = 0; i < n; i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jo = ja.getJSONObject(i);

                System.out.println("-->> "+jo.getString("tenantid"));
       
                DriverManagerDataSource datasource = getata(jo.getString("url"), jo.getString("username"),jo.getString("password"),jo.getString("driver"));
                    
                hashMap.put("tenantId"+jo.getString("tenantid"), datasource); 
                temporal++;

            }   


        }catch(IOException e){
            
        }
        
   
        return hashMap;
    }

    
    public static DriverManagerDataSource getata(String url, String username, String password, String driver){

        System.out.println("DATA SROUCE "+url);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);


        return dataSource;

    }



}

