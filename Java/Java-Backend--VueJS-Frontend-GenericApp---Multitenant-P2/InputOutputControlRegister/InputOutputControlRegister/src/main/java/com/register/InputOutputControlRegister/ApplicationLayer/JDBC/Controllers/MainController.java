package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.List;
import java.util.Map;

import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Dao.CargoPerfilesDAO;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Cargo;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.PerfilCargo;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Permisions;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.RegistroEntradaSalida;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Users;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Usuarios;
import com.register.InputOutputControlRegister.JwtSecurityLayer.Security.UserPrincipal;

@RestController
@RequestMapping(value = { "/register" })
public class MainController{

    @Autowired CargoPerfilesDAO cargodao;
    @Autowired CountingTime countingtime;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping({ "/users" })
    public String methodThird() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = ((UserPrincipal) authentication.getPrincipal()).getEmail();
        System.out.println("this is the USER --> "+currentPrincipalName);
        return "Hello FROM USER --> "+currentPrincipalName;
    }


    
    @PostMapping({"/createperfilcargo"})
	public ResponseEntity<Void> addPerfilCargo(@RequestBody Cargo payload) {
        
        //System.out.println("data ....--> "+payload.nombre);
        cargodao.createPerfilCargo(payload.cargoNombre);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @PostMapping({"/createcargo"})
	public ResponseEntity<Void> addCargo(@RequestBody Cargo payload) {
        
        //System.out.println("data ....--> "+payload.nombre);
        cargodao.createCargo(payload.fkIdPerfilCargo,payload.cargoNombre);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}


    @PostMapping({"/createusuario"})
	public ResponseEntity<Void> addUsuario(@RequestBody Usuarios payload) {
        
        if(cargodao.checkingUsuer(payload.cedula)){

            cargodao.createUsuario(payload.fkIdCargo,payload.cedula,payload.nombre,payload.celular,payload.email);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }else{

            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }

        
	}

    @PostMapping({"/registro"})
	public ResponseEntity<Void> addRegistro(@RequestBody RegistroEntradaSalida payload) {
        
        cargodao.createRegistroEntradaSalida(payload.cedula,payload.tipoRegistro,payload.fechaRegistro,payload.fechaconhoraRegistro);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping({"/permision"})
	public ResponseEntity<Void> addPermiso(@RequestBody Permisions payload) {
        
        if(cargodao.createPermision(payload)){

            return new ResponseEntity<Void>(HttpStatus.OK);

        }else{

            return new ResponseEntity<Void>(HttpStatus.DESTINATION_LOCKED);

        }

        
    }
    

    @GetMapping("/perfilcargos") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<List<PerfilCargo>> getPerfilCargos(){

        List<PerfilCargo> rt = cargodao.getPerfilCargo();
		return new ResponseEntity<List<PerfilCargo>>(rt, HttpStatus.OK);
    }


    @GetMapping("/cargos/{idperfil}") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<List<Cargo>> getCargos(@PathVariable("idperfil") String idPerfil){

        List<Cargo> rt = cargodao.getCargos(idPerfil);
		return new ResponseEntity<List<Cargo>>(rt, HttpStatus.OK);
    }

    @PostMapping({"/createuser"})
	public ResponseEntity<Void> addUser(@RequestBody Users payload) {
        

        System.out.println("PERFIL -> "+payload.getFkIdPerfil());
        System.out.println("PERFIL -> "+payload.getFkIdCargo());
        System.out.println("PERFIL -> "+payload.getCedula());
        System.out.println("PERFIL -> "+payload.getNombre());
        System.out.println("PERFIL -> "+payload.getHuella());


        cargodao.createUser(payload.getFkIdPerfil(), payload.getFkIdCargo(), payload.getCedula(), payload.getNombre(),payload.getHuella());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @GetMapping("/getusers") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<List<Usuarios>> getUsuarios(){

        List<Usuarios> rt = cargodao.getUsuarios();
		return new ResponseEntity<List<Usuarios>>(rt, HttpStatus.OK);
    }


    @GetMapping("/service") //ROTACION DE PRODUCTOS CON BASE A UN LAB
	public ResponseEntity<Void> getCargos() {

        
        countingtime.satartCounting();
		return new ResponseEntity<Void>(HttpStatus.OK);
    }   




    //************************ TESTING FOR DELETING*/


    @PostMapping({"/testing"})
	public ResponseEntity<Void> testingTimes(@RequestBody Users payload) {
        

    
        cargodao.createRecord(payload.getFkIdPerfil(), payload.getFkIdCargo(), payload.getCedula(),payload.getNombre());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    //************************ TESTING FOR DELETING*/

}