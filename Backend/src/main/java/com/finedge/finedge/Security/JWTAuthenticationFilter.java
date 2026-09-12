package com.finedge.finedge.Security;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

               System.out.println("JWT Filter Executed");
               String authHeader  = request.getHeader("Authorization");
               System.out.println(authHeader);

               if(authHeader== null || !authHeader.startsWith("Bearer ")){
                 filterChain.doFilter(request, response);
                 return;
               }

               String jwt = authHeader.substring(7);

               String userName = jwtService.extractUsername(jwt);
               System.out.println("Username = " + userName);

               if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                      

                      System.out.println("After setAuthentication = "
        + SecurityContextHolder.getContext().getAuthentication());
                       UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(userName);
                       System.out.println("User = " + userDetails.getUsername());

                      boolean valid = jwtService.isTokenValid(jwt, userDetails);

                        System.out.println("Token Valid = " + valid);

                        if(valid){

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

                        
                            System.out.println(
                                "After setting = " +
                                SecurityContextHolder.getContext().getAuthentication()
                            );

                     }
               }

               filterChain.doFilter(request, response);

               System.out.println("After doFilter = "
        + SecurityContextHolder.getContext().getAuthentication());

             


    }
    
}
