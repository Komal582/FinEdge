package com.finedge.finedge.Security;


import com.finedge.finedge.Component.CustomLoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     private final JWTAuthenticationFilter jwtAuthentication;
     private final CustomLoginSuccessHandler customLoginSuccessHandler;

     public SecurityConfig(JWTAuthenticationFilter jwtAuthentication, CustomLoginSuccessHandler customLoginSuccessHandler, CustomUserDetailsServiceImpl customUserDetailsServiceImpl){
        this.jwtAuthentication =jwtAuthentication;
        this.customLoginSuccessHandler = customLoginSuccessHandler;
        this.customUserDetailsServiceImpl = customUserDetailsServiceImpl ;

     }

     private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl ;

    

      @Bean
       public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                 http.headers(headers -> headers
                             .cacheControl(cache -> {})
                     )
                     .sessionManagement(session->
                          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                     )
                     . csrf(csrf->csrf.disable())
                     .authorizeHttpRequests(auth->
                             auth.requestMatchers("/",
                                     "/user/signup",
                                     "/user/home",
                                     "/user/userSuccess",
                                     "/user/register",
                                    "/user/login"

                                     ).permitAll()

                                     .anyRequest().authenticated())
                     .formLogin(form->form
                             .loginPage("/login")
                             .loginProcessingUrl("/do-login")
                             .successHandler(customLoginSuccessHandler)
                             .failureUrl("/login?error=true")
                             .permitAll()

                     )
                     .addFilterBefore(
                        jwtAuthentication,
                        UsernamePasswordAuthenticationFilter.class
                     )
                     .logout(logout->logout
                             .logoutSuccessUrl("/"));
                      


                     return http.build();

      }

  

      @Bean
      public PasswordEncoder passwordEncoder(){


          return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationManager authenticationManager( CustomUserDetailsServiceImpl customUserDetailsServiceImpl,PasswordEncoder passwordEncoder){
             DaoAuthenticationProvider doaAuthenticationProvider = new DaoAuthenticationProvider();

             doaAuthenticationProvider.setUserDetailsService(customUserDetailsServiceImpl);
             System.out.println("in Auth");
             doaAuthenticationProvider.setPasswordEncoder(passwordEncoder);

             return new ProviderManager(doaAuthenticationProvider);


      }

}
