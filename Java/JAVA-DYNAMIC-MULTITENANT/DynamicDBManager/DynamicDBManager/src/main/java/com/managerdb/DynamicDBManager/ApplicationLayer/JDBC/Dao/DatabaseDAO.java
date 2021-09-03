package com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Model.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public class DatabaseDAO{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Database> getAllUsers(){


        List<Database> allUsers = new ArrayList<>();

        String queryTotal = "select password, tenant_id, url, username,driver from tenant_details";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal);
 
            for (Map row : rows) {

                allUsers.add(new Database(row.get("url").toString(),row.get("username").toString(),row.get("password").toString(),row.get("tenant_id").toString(),row.get("driver").toString()));
            }


        return allUsers;
    }



}