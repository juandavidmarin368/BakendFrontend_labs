package com.api2.api2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = { "/api2" })

public class MainController {

    @GetMapping("/hello") //
    public ResponseEntity<String> getHifromApi1() {

        try {
            Process p = Runtime.getRuntime()
                    .exec(new String[] { "bash", "-c", "/usr/bin/stress --cpu 1 --io 2 --vm 1 --vm-bytes 100mb" });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String rt = "Hello from API2!";
		return new ResponseEntity<String>(rt, HttpStatus.OK);

    }


}