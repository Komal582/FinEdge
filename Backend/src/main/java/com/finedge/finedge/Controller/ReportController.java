package com.finedge.finedge.Controller;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finedge.finedge.DTO.ExpenseCategoryReport;
import com.finedge.finedge.DTO.MonthlyReport;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Service.ExpenseService;
import com.finedge.finedge.Service.ReportService;
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final ExpenseService expenseService;

    public ReportController(ReportService reportService,ExpenseService expenseService){
        this.reportService=reportService;
        this.expenseService=expenseService;
    }
    
    @GetMapping("/monthly")
    public List<MonthlyReport> getMonthlyReport(@RequestParam(value="year") Integer year, Authentication authentication ){
         User user =(User)authentication.getPrincipal();

         return reportService.generateReport(user,year);

    }

    @PostMapping("/expense")
    public HashMap<String,Long> getExpenseCategoryReport(@RequestBody ExpenseCategoryReport expenseCategoryReport, Authentication authentication ){
         User user =(User)authentication.getPrincipal();
        Integer year =ExpenseCategoryReport.getYear();
        List<String> category=ExpenseCategoryReport.getCategory();

         return expenseService.getExpenseAmountByCategory(user,year,category);

    }
}
