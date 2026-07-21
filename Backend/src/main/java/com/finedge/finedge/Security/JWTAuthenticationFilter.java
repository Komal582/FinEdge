package com.finedge.finedge.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.web.servlet.resource.HttpResource;
import jakarta.servlet.ServletException;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{


    private final JWTService jwtService;
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl ;


    public JWTAuthenticationFilter(JWTService jwtService, CustomUserDetailsServiceImpl customUserDetailsServiceImpl){
        this.jwtService = jwtService;
        this.customUserDetailsServiceImpl= customUserDetailsServiceImpl;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException{
               String authHeader  = request.getHeader("Authorization");

               if(authHeader== null || !authHeader.startsWith("Bearer")){
                 filterChain.doFilter(request, response);
                 return;
               }

               String jwt = authHeader.substring(7);

               String userName = jwtService.extractUsername(jwt);

               if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                       UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(userName);

                        if(jwtService.isTokenValid(jwt,userDetails)){

                        UsernamePasswordAuthenticationToken authToken =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails,
                                            null,
                                            userDetails.getAuthorities()
                                    );

                        authToken.setDetails(
                                        new WebAuthenticationDetailsSource()
                                                .buildDetails(request)
                                );

                        SecurityContextHolder.getContext()
                                    .setAuthentication(authToken);

                     }
               }

             


    }
    
}
