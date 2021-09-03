package com.docker.PersisterDataDocker.App;

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
@RequestMapping(value = { "/docker" })

public class MainController{

    @Autowired MainDao cargodao;



    @PostMapping({ "/createitems/{itemname}" })
    public ResponseEntity<Void> updateEmpresa(@PathVariable("itemname") String itemname) {

            cargodao.createItem(itemname);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/items") //
	public ResponseEntity<List<Models>> getEmpresas(){

        List<Models> rt = cargodao.getEmpresas();
		return new ResponseEntity<List<Models>>(rt, HttpStatus.OK);
    }
   
    @GetMapping("/itemspaginados/{page}") //
	public ResponseEntity<ItemsPaginados> getItemsPaginados(@PathVariable("page") int page){

        ItemsPaginados rt = cargodao.getPaginados(page);
		return new ResponseEntity<ItemsPaginados>(rt, HttpStatus.OK);
    }


    @GetMapping("/hello") //
	public ResponseEntity<String> getVoid(){

        String rt = "Juts testing the way it works!";
		return new ResponseEntity<String>(rt, HttpStatus.OK);
    }

}