package com.finedge.finedge.Service.Impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.ExpenseRepository;
import com.finedge.finedge.Service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
 
    @Override
    public List<Expense> getExpenseHistory(User user){
       return expenseRepository.getByUser(user);
    }

    @Override
    public boolean addExpense(Expense expense){
         expenseRepository.save(expense);
         System.out.println(expense.getExpense_id());
        System.out.println(expense.getAmount());
        System.out.println(expense.getCategory());

         return true;
    }

    @Override
    public Integer getExpenseAmountByUser(User user){


        Integer expense=expenseRepository.getExpenseAmountByUser(user);
        System.out.println("Expense"+ expense);

       if(expense==null){
         return 0;
       }
       else{
        return expense;
       }


    }


   @Override 
   public HashMap<String,Long> getExpenseAmountByCategory(User user, Integer year, List<String> category){
      HashMap<String,Long> map = new HashMap<>();
      LocalDate startDate = LocalDate.of(year,1,1);
      LocalDate endDate = LocalDate.of(year,12,12);
      for(int i=0;i<category.size();i++){
         Long amount=expenseRepository.getTotalExpenseByCategory(user,category.get(i),startDate,endDate).orElse(0L);
         System.out.println("Category"+category.get(i));
         map.put(category.get(i),amount);
      }
      return map;
   }


}
