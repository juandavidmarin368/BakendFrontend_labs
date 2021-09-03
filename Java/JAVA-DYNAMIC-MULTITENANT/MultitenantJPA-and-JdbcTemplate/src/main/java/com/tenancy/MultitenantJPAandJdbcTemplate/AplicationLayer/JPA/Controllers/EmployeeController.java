package com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Controllers;




import org.springframework.web.bind.annotation.PostMapping;

import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Models.Employee;
import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Servicies.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * Created by Java Developer Zone on 19-07-2017.
 */
@RestController
@RequestMapping(value = { "/create" })
public class EmployeeController {
    @Autowired
    EmployeeService employeeDAO;
    

    @PostMapping(value = "/user")
    public ResponseEntity<Void> createItem(@RequestBody Employee item,UriComponentsBuilder builder) {

        employeeDAO.createEmployee(item);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}