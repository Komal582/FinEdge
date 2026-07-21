package com.finedge.finedge.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test-auth")
    @ResponseBody
    public String testAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated user = " + auth.getName() +
                ", Authorities = " + auth.getAuthorities();
    }

}
