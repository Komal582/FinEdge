package com.finedge.finedge.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addExpense")
    public Boolean addExpense(@ModelAttribute Expense expense, Authentication authentication){

        User user =(User)authentication.getPrincipal();
        
        expense.setUser(user);

       boolean flag=expenseService.addExpense(expense);

        if(flag) {
              
             return true;
        }
        else{
           return false;
        }
    }

    @GetMapping("/history")
    public List<Expense> getExpenseHistory(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        List<Expense> expense = expenseService.getExpenseHistory(user);
      
        return expense;  

    }
}
