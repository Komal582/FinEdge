package com.finedge.finedge.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finedge.finedge.Model.User;
import com.finedge.finedge.Service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

         @Autowired
         private UserService userService;

         @Autowired
         private PasswordEncoder passwordEncoder;



         @GetMapping("/signup")
         public String signupPage(){
             return "userSignup";
         }

         @PostMapping("/register")
         public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,HttpSession session){
             String hashedPassword = passwordEncoder.encode(password);
             String role="USER";

             User user = new User();
             user.setUsername(name);
             user.setRole(role);
             user.setEmail(email);
             user.setPassword(hashedPassword);

             userService.saveUser(user);

            System.out.println("IN Register page");
             return "redirect:/user/userSuccess";

         }

        @ResponseBody
        @PutMapping("/updateUser")
        public String updateUser(Model model,@RequestBody User user,Authentication authentication){
            
            User session_user = (User)authentication.getPrincipal();
            
          
            String pass= user.getPassword();
            Long user_id = session_user.getUser_id();
            
           

            String hashedPassword = passwordEncoder.encode(pass);
            user.setPassword(hashedPassword);
            user.setUser_id(user_id);


            if(userService.updateUser(user)){
                 return "User updated Successfully";
            }
            else{
                return "User not updated ";
            }
            
            
          
            
        }



         @GetMapping("/userSuccess")
         public String SuccessPage(){
             return "userSuccess";
         }




         @GetMapping("/update")
         public String update(){

             return "userUpdate";
         }



         @GetMapping("/dashboard")
         public String userDashboard(){
               return "user_dashboard";
         }



}
