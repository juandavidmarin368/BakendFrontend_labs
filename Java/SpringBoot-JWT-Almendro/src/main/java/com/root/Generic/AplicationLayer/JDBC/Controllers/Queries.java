package com.root.Generic.AplicationLayer.JDBC.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.root.Generic.AplicationLayer.JDBC.Dao.RotaciondeProductosDAO;
import com.root.Generic.AplicationLayer.JDBC.Models.Laboratorios;
import com.root.Generic.AplicationLayer.JDBC.Models.RotacionProductosFinal;
import com.root.Generic.AplicationLayer.JDBC.Models.Totalventasdiarias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("get")
public class Queries{


    @Autowired
    RotaciondeProductosDAO rotation;

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("rotation/{year}/{id}/{page}") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<List<RotacionProductosFinal>> getRotacion2(@PathVariable("year") String year,@PathVariable("id") Long id, @PathVariable("page") Integer page){

        List<RotacionProductosFinal> rt = rotation.getRotacionproductos(id,page, year);
		return new ResponseEntity<List<RotacionProductosFinal>>(rt, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("labs") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<List<Laboratorios>> labs(){

        List<Laboratorios> rt = rotation.getLabs();
		return new ResponseEntity<List<Laboratorios>>(rt, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("totalventasdiarias/{morningAfternoon}/{page}")
    public ResponseEntity<List<Totalventasdiarias>> getTotalventasdiarias(@PathVariable("morningAfternoon") String morningAfternoon, @PathVariable("page") Integer page){

        List<Totalventasdiarias> totalventasDiarias = rotation.getTotalventasdiariasconPaginacion(morningAfternoon,page);
        return new ResponseEntity<List<Totalventasdiarias>>(totalventasDiarias, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("totalmonth/{month}")
    public ResponseEntity<String> getTotalMonth(@PathVariable("month") String month){

        return new ResponseEntity<String>(rotation.getTotalmonth(month), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("totalhoy")
    public ResponseEntity<String> getTotalhoy(){

        return new ResponseEntity<String>(rotation.getTotalhoy(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("totalventassolohoy/{morningAfternoon}")
    public ResponseEntity<String> getsoloTotalventasdiarias(@PathVariable("morningAfternoon") String morningAfternoon){

        return new ResponseEntity<String>(rotation.getsoloTotalventasdiarias(morningAfternoon), HttpStatus.OK);
    }


    
}