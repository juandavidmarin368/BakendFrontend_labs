package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Dao.CargoPerfilesDAO;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Cargo;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.RegistroEntradaSalida;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Usuarios;
import com.register.InputOutputControlRegister.JwtSecurityLayer.Security.UserPrincipal;


@RestController
@RequestMapping(value = { "/test2" })
public class NewController{


    

}