package com.root.Generic.JwtSecurityLayer.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import com.root.Generic.JwtSecurityLayer.Exceptions.AppException;
import com.root.Generic.JwtSecurityLayer.Models.Role;
import com.root.Generic.JwtSecurityLayer.Models.RoleName;
import com.root.Generic.JwtSecurityLayer.Models.User;
import com.root.Generic.JwtSecurityLayer.Payload.ApiResponse;
import com.root.Generic.JwtSecurityLayer.Payload.JwtAuthenticationResponse;
import com.root.Generic.JwtSecurityLayer.Payload.LoginRequest;
import com.root.Generic.JwtSecurityLayer.Payload.SignUpRequest;
import com.root.Generic.JwtSecurityLayer.Repositories.RoleRepository;
import com.root.Generic.JwtSecurityLayer.Repositories.UserRepository;
import com.root.Generic.JwtSecurityLayer.Security.JwtTokenProvider;
import com.root.Generic.JwtSecurityLayer.Security.UserPrincipal;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        JwtTokenProvider tokenProvider;

        @Autowired
        JdbcTemplate jdbcTemplate;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                                                loginRequest.getPassword()

                                ));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            
                String idUsername = ((UserPrincipal) authentication.getPrincipal()).getId().toString();


                String query ="select role_id from user_roles where user_id=" + idUsername;
                List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
                for (Map row : rows) {

                        System.out.println("The user roles are ..."+row.get("role_id"));
                }

                String jwt = tokenProvider.generateToken(authentication);
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,rows));
        }

        // when using the endpoint if you are going to create an user in a localhost, just do it in this way
        // end-user: http://localhost:7078/api/auth/signup/user
        // admin: http://localhost:7078/api/auth/signup/admin
        // superadmin: http://localhost:7078/api/auth/signup/superadmin

        @PostMapping("/signup/{role}")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                        @PathVariable("role") String role) {

                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                Role userRole;
                User result = null;

                if (role.equals("user")) {

                        userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new AppException("User Role not set."));
                                user.setRoles(Collections.singleton(userRole));
                                result = userRepository.save(user);
                }
                if (role.equals("admin")) {

                        userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new AppException("User Role not set."));
                                user.setRoles(Collections.singleton(userRole));
                                result = userRepository.save(user);
                }
                if (role.equals("superadmin")) {

                        userRole = roleRepository.findByName(RoleName.ROLE_SUPERADMIN)
                                .orElseThrow(() -> new AppException("User Role not set."));
                                user.setRoles(Collections.singleton(userRole));
                                result = userRepository.save(user);
                }

               
                

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(true, "User ->"+role+"<- registered successfully"));
        }

     

}
