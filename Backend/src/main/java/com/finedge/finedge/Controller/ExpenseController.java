package com.finedge.finedge.Controller;


import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public String viewExpense(HttpSession session){



        return "userExpense";
    }

    @PostMapping("/addExpense")
    public String addExpense(RedirectAttributes redirectAttributes, @ModelAttribute Expense expense, Authentication authentication){

        User user =(User)authentication.getPrincipal();


        expense.setUser(user);

        if(expenseService.addExpense(expense)){
            redirectAttributes.addFlashAttribute("message","Expense added successfully");
        }
        else{
             redirectAttributes.addFlashAttribute("message","Expense not saved Successfully");
        }

        return "redirect:/expense/view";
    }
}
