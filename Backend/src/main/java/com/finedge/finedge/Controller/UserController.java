package com.finedge.finedge.Controller;


import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finedge.finedge.DTO.DashboardResponse;
import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Service.BalanceService;
import com.finedge.finedge.Service.ExpenseService;
import com.finedge.finedge.Service.IncomeService;
import com.finedge.finedge.Service.TransactionService;
import com.finedge.finedge.Service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    
         private final UserService userService;
         private final PasswordEncoder passwordEncoder;
         private final ExpenseService expenseService;
         private final IncomeService incomeService;
         private final BalanceService balanceService;
         private final TransactionService transactionService;


        public UserController(TransactionService transactionService, BalanceService balanceService,ExpenseService expenseService, IncomeService incomeService,UserService userService, PasswordEncoder passwordEncoder){
            this.expenseService = expenseService;
            this.incomeService = incomeService;
            this.passwordEncoder=passwordEncoder;
            this.userService =userService;
            this.balanceService = balanceService;
            this.transactionService = transactionService;
         }

         @PostMapping("/register")
         public Boolean registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password){
             String hashedPassword = passwordEncoder.encode(password);
             String role="USER";

             User user = new User();
             user.setUsername(name);
             user.setRole(role);
             user.setEmail(email);
             user.setPassword(hashedPassword);

             if(userService.saveUser(user)){
                return true;
             }
             else{
                return false;
             }
         }

        @ResponseBody
        @PutMapping("/updateUser")
        public String updateUser(@RequestBody User user,Authentication authentication){
            
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
   


         @PostMapping("/dashboard")
         public DashboardResponse dashboard( Authentication authentication ){


           
            User user =(User)authentication.getPrincipal();
            Integer balanceAmount = balanceService.getBalanceById(user);
            System.out.println("Balance"+balanceAmount); 
           
            Integer income = incomeService.getIncomeAmountByUser(user);
           
            Integer expense = expenseService.getExpenseAmountByUser(user);

            List<Razorpay_payment> userLatestTranscationList =transactionService.getLatestUserTranscation(user.getUser_id());
            
            
               return new DashboardResponse(
                  user.getUsername(),
                  balanceAmount,
                  income,
                  expense,
                  userLatestTranscationList
               );

           
                    
         }



}
