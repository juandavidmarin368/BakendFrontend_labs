package com.docker.PersisterDataDocker.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;





@Repository
public class MainDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;


   
    public void createItem(String name){


            String query = "insert into items(name) values('"+name+"')";
            jdbcTemplate.update(query);

    }

    public List<Models> getEmpresas() {

        List<Models> preres = new ArrayList<>();
        String query = "select * from items";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {

            preres.add(new Models( row.get("id").toString(), row.get("name").toString()));
           
        }

        return preres;

    }

    public ItemsPaginados getPaginados(int page) {

        if(page>0){

            page--;
        }

        int totalItems = jdbcTemplate.queryForObject("select count(id) from items", Integer.class);
        int totalPages = totalItems / 10;
        String query = "select * from items LIMIT 10 OFFSET " + page * 10;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<HashMap<String, String>> list = new ArrayList<>();
        for (Map row : rows) {


            Map<String, String> infoUsuario = new HashMap<String, String>();
            infoUsuario.put("id",row.get("id").toString());
            infoUsuario.put("name",row.get("name").toString());
           
            list.add(new HashMap(infoUsuario));
        
        }

        ItemsPaginados it = new ItemsPaginados(String.valueOf(totalItems),String.valueOf(totalPages+1),list);
        return it;
    }


}