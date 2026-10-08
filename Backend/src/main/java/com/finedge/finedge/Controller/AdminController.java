package com.finedge.finedge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.finedge.finedge.DTO.AdminDashboardResponse;
import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public AdminDashboardResponse adminDashboard( Authentication authentication){
        User user =(User)authentication.getPrincipal();

        long total_users =adminService.getTotalUser();
        long total_transactions=adminService.getTotalTransactions();
        long total_income=adminService.getTotalIncome();
        long total_expense=adminService.getTotalExpense();
        
        List<User> userList = adminService.getLatestUser();
        List<Razorpay_payment> transactionList = adminService.getLatestTransaction();

        return new AdminDashboardResponse(user.getUsername(),user.getEmail(),total_users, total_transactions, total_income, total_expense,userList,transactionList);
    }

   

    @GetMapping("/userlist")
    public List<User> userlist() {

          List<User> users = adminService.getAllUser();

        return users;
    }

    @GetMapping("/transcations")
    public List<Razorpay_payment> transcation(){

       
       
        List<Razorpay_payment> transcationList = adminService.getAllTranscation();
        return transcationList;
    }

    @GetMapping("/balance")
    public List<Balance> balance(){

        List<Balance> balance = adminService.getAllBalance();
      

        return balance;
    }

    @GetMapping("/income")
    public List<Income> income(){

        List<Income> income = adminService.getAllIncome();
       

        return income;
    }
    @GetMapping("/expense")
    public List<Expense> expense(){

        List<Expense> expense = adminService.getAllExpense();
    

        return expense;
    }

}
