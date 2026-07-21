package com.finedge.finedge.Component;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finedge.finedge.DTO.LoginResponse;
import com.finedge.finedge.Security.JWTService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTService jwtService;
    private final ObjectMapper objectMapper ;

    public CustomLoginSuccessHandler(JWTService jwtService, ObjectMapper objectMapper ){
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal(); 

        String token =jwtService.generateToken(userDetails);

        response.setContentType("application/json");

        LoginResponse loginResponse = new LoginResponse(token);

        String json = objectMapper.writeValueAsString(loginResponse);

        response.setContentType("application/json");

        response.getWriter().write(json);




    }
}
