package com.root.Generic.AplicationLayer.JPA.Controllers;

import java.util.List;

import com.root.Generic.AplicationLayer.JPA.Models.Item;
import com.root.Generic.AplicationLayer.JPA.Servicies.ItemService;
import com.root.Generic.AplicationLayer.JPA.Servicies.LaboratoriosService;
import com.root.Generic.JwtSecurityLayer.Security.CustomUserDetailsService;
import com.root.Generic.JwtSecurityLayer.Security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.root.Generic.JwtSecurityLayer.Security.UserPrincipal;

@RestController
@RequestMapping(value = { "/items" })
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    LaboratoriosService labService;

    

    
    //NativeSqlQueries nativeSql = new NativeSqlQueries();
    

    @PostMapping(value = "/create")
    public ResponseEntity createItem(@RequestBody Item item, UriComponentsBuilder ucBuilder) {

        itemService.createItem(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/item/{id}").buildAndExpand(item.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('SUPERADMIN')")
    @RequestMapping({ "/superadmin" })
    public String firstPage() {

        
         //--------------CODE FOR GETTING THE INFO DATA FROM THE CURRENT LOGED IN USER FROM THE CONTEXT
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String currentPrincipalName = ((UserPrincipal) authentication.getPrincipal()).getEmail();
 
         System.out.println("this is tHE MAIN ONE ..."+currentPrincipalName);
        return "Hello World FROM SUPER ADMIN";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping({ "/admin" })
    public String secondtPage() {
        return "Hello World FROM ADMIN";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping({ "/user" })
    public String thirdPage() {


       
        //--------------CODE FOR GETTING THE INFO DATA FROM THE CURRENT LOGED IN USER FROM THE CONTEXT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = ((UserPrincipal) authentication.getPrincipal()).getEmail();

        System.out.println("this is tHE MAIN ONE ..."+currentPrincipalName);
        return "Hello World FROM USER -- ";
    }


   
    /*@GetMapping(value = "/labs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RotacionProductos> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        List<RotacionProductos> user = nativeSql.getRotaciondeproductos(id);
      
        return new ResponseEntity<RotacionProductos>(HttpStatus.OK);
    }

    */
}