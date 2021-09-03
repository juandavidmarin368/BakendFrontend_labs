package com.example.Pagination.JDBC.Dao;

import java.util.*;


import com.example.Pagination.JDBC.Models.FinalProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RotationProducts{


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<FinalProducts> getRecordwithPagination(int page){

        Random random = new Random();
        String query="";
        int limitItemsPerPage=5;
        List<FinalProducts> finalProducts = new ArrayList<>();
        String queryTotalItems = "select count(id) from products ";
        int totalItems = jdbcTemplate.queryForObject(queryTotalItems, Integer.class);
        int totalPages = (totalItems/5)-1;
        query="select * from products LIMIT "+limitItemsPerPage+" OFFSET "+page*limitItemsPerPage;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<HashMap<String, String>> newList = new ArrayList<>();
        String productName="";
        String productId="";
        String dayWeeks[] ={"Monday","Thuesday","Wenedsay","Thursday","Friday","Saturday","Sunday"};

        for (Map row : rows) {

            HashMap<String, String> map = new HashMap<String, String>();
            productName=row.get("name").toString();
            productId=row.get("code").toString();
            newList.clear();
            for(int a=0;a<=6;a++){

                map.put("day", dayWeeks[a]);
                map.put("quantity",String.valueOf(random.nextInt(100)));
                newList.add(new HashMap(map));
            }
           
            finalProducts.add(new FinalProducts(productName,productId,String.valueOf(totalPages),newList));
           
        }


        return finalProducts;
    }

}