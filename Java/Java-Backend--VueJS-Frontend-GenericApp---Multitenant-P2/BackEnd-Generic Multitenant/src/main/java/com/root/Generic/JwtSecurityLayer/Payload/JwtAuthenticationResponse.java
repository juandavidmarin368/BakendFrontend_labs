package com.root.Generic.JwtSecurityLayer.Payload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private List<Map<String, Object>> roles;
    private Map<String, String> infoEmpresa;

    public Map<String,String> getInfoEmpresa() {
        return this.infoEmpresa;
    }

    public void setInfoEmpresa(Map<String,String> infoEmpresa) {
        this.infoEmpresa = infoEmpresa;
    }



   

    public List<Map<String,Object>> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Map<String,Object>> roles) {
        this.roles = roles;
    }
   
    
    public JwtAuthenticationResponse(String accessToken, List<Map<String, Object>> rows,Map<String, String> infoEmpresa) {
        this.accessToken = accessToken;
        this.roles = rows;
        this.infoEmpresa = infoEmpresa;
        
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}