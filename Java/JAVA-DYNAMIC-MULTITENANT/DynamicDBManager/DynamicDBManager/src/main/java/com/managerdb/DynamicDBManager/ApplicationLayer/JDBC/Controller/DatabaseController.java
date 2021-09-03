package com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Dao.DatabaseDAO;
import com.managerdb.DynamicDBManager.ApplicationLayer.JDBC.Model.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("dynamic")
public class DatabaseController{

    @Autowired
    private DatabaseDAO userDao;


    @GetMapping("/databases")
	public ResponseEntity<List<Database>> getArticleById() {
    
		List<Database> user = userDao.getAllUsers();
		return new ResponseEntity<List<Database>>(user, HttpStatus.OK);
    }
    

}