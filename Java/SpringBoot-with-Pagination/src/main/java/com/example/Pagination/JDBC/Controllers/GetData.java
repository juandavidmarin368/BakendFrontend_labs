package com.example.Pagination.JDBC.Controllers;

import com.example.Pagination.JDBC.Dao.RotationProducts;
import com.example.Pagination.JDBC.Models.FinalProducts;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("get")
public class GetData{

    @Autowired
    RotationProducts rotationProducts;


    @GetMapping("products/{id}")
    public ResponseEntity<List<FinalProducts>> getRotationProducts(@PathVariable("id") Integer id){

        List<FinalProducts> rt = rotationProducts.getRecordwithPagination(id);

        return new ResponseEntity<List<FinalProducts>>(rt,HttpStatus.OK);
    }

}