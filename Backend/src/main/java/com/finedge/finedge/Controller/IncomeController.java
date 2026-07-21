package com.finedge.finedge.Controller;


import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.IncomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public String userIncome(HttpSession session){


        return "userIncome";
    }

    @PostMapping("/incomeAdded")
    public String addIncome(RedirectAttributes redirectAttributes, @RequestParam Integer amount, @RequestParam String source, @RequestParam String notes, @RequestParam LocalDate incomeDate, Authentication authentication){

        User user =(User)authentication.getPrincipal();




        Income income = new Income();
        income.setAmount(amount);
        income.setNote(notes);
        income.setSource(source);
        income.setDate(incomeDate);
        income.setUser(user);


        boolean flag =incomeService.saveIncome(income);

        if(flag) {
            redirectAttributes.addFlashAttribute("message", "Income saved successfully!");
            return "redirect:/income/view";
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Income not saved successfully!");
            return "redirect:/income/view";
        }

    }


}
