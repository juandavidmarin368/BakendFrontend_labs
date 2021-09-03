package com.root.Generic.JwtSecurityLayer.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.root.Generic.JwtSecurityLayer.Security.UserPrincipal;

@RestController
@RequestMapping(value = { "/test" })
public class TestController{


    
    

    @PreAuthorize("hasRole('SUPERADMIN') OR hasRole('USER')")
    @RequestMapping({ "/forsuperadmins" })
    public String methodOne() {

        
        return "Hello FROM SUPER ADMIN OR USER --> ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping({ "/foradmins" })
    public String methodTwo() {

        
        return "Hello FROM ADMIN ";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping({ "/forusers" })
    public String methodThird() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = ((UserPrincipal) authentication.getPrincipal()).getEmail();
        System.out.println("this is the USER --> "+currentPrincipalName);
        return "Hello FROM USER --> "+currentPrincipalName;
    }
    
    

}