package com.finedge.finedge.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginResponse {
    
    private String token ;
    private String role;

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(String token, UserDetails userDetails) {
        this.role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER"); 
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
