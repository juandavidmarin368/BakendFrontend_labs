package com.example.GenericTomcat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("get")
public class NewController{


    @GetMapping("hello")
    public ResponseEntity<String> getTotalhoy(){

        return new ResponseEntity<String>("Returning a string through Spring boot with SSL --> [OK]", HttpStatus.OK);
    }


}