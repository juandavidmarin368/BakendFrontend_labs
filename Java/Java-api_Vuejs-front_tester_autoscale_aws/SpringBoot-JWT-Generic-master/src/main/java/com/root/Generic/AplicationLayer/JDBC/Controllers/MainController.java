package com.root.Generic.AplicationLayer.JDBC.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping(value = { "/api3" })
public class MainController {

     @GetMapping("/teststring") //
     public ResponseEntity<String> getEmpresas() {

          return new ResponseEntity<String>("SI PUEDE LEER ESTE MENSAJE, ESTA USANDO CORRECTAMENTE EL JWT!..",
                    HttpStatus.OK);
     }

     @GetMapping("/gettingip") //
     public ResponseEntity<String> getinGiP() {

          String currentIp="";
          try {
               InetAddress inetAddress = InetAddress.getLocalHost();
               currentIp = "IP Address:- " + inetAddress.getHostAddress();
          } catch (UnknownHostException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
       
		return new ResponseEntity<String>(currentIp, HttpStatus.OK);
    }


}