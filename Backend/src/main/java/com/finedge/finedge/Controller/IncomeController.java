package com.finedge.finedge.Controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.IncomeService;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/incomeAdded")
    public Boolean addIncome(@RequestParam Integer amount, @RequestParam String source, @RequestParam String notes, @RequestParam LocalDate incomeDate, Authentication authentication){

        User user =(User)authentication.getPrincipal();
        Map<String,Object> map = new HashMap<>();



        Income income = new Income();
        income.setAmount(amount);
        income.setNote(notes);
        income.setSource(source);
        income.setDate(incomeDate);
        income.setUser(user);


        boolean flag =incomeService.saveIncome(income);

        if(flag) {
              
             return true;
        }
        else{
           return false;
        }

    }

    @GetMapping("/history")
    public List<Income> getIncomeHistory(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        List<Income> income = incomeService.getIncomeHistory(user);
      
        return income;  

    }


}
