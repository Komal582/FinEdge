package com.finedge.finedge.Service.Impl;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.finedge.finedge.DTO.MonthlyReport;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.ExpenseRepository;
import com.finedge.finedge.Repository.IncomeRepository;
import com.finedge.finedge.Repository.Razorpay_paymentRepository;
import com.finedge.finedge.Service.ReportService;



@Service
public class ReportServiceImpl implements ReportService{
   private final IncomeRepository inomeRepository;
   private final ExpenseRepository expenseRepository;
   private final Razorpay_paymentRepository razorpay_paymentRepository;
   
   public ReportServiceImpl(IncomeRepository inomeRepository,ExpenseRepository expenseRepository,Razorpay_paymentRepository razorpay_paymentRepository){
     this.inomeRepository=inomeRepository;
     this.expenseRepository= expenseRepository;
     this.razorpay_paymentRepository=razorpay_paymentRepository;
   } 

   @Override
   public List<MonthlyReport> generateReport(User user,int year){

    List<MonthlyReport> list = new ArrayList<>();
    System.out.println("Year"+year);
    LocalDate startDate = LocalDate.of(year,1,1);
    
    for(int i=1;i<=12;i++){
    LocalDate endDate= startDate.plusMonths(1);
    Long income=inomeRepository.getTotalIncomeByDateRange(user,startDate,endDate).orElse(0L);
    System.out.println("Monhly Income"+income);
    Long expense=expenseRepository.getTotalExpenseByDateRange(user,startDate,endDate).orElse(0L);
    Long payment=razorpay_paymentRepository.getTotalRazorpayPaymenByDateRange(user,startDate,endDate).orElse(0L);
    Long savings =income-(expense+payment);
    String month=startDate.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH);
    startDate=endDate;
    
    list.add(new MonthlyReport(month,income,expense,payment,savings));  
   }

   return list;

  }

}
