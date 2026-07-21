package com.finedge.finedge.Service.Impl;


import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.ExpenseRepository;
import com.finedge.finedge.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Boolean addExpense(Expense expense){
         expenseRepository.save(expense);
         System.out.println(expense.getExpense_id());
        System.out.println(expense.getAmount());
        System.out.println(expense.getCategory());

         return true;
    }

    @Override
    public Integer getExpenseAmountByUser(User user){



        Optional<Integer> expense=expenseRepository.getExpenseAmountByUser(user);

        return expense.orElseThrow(()-> new RuntimeException("Expense not found"));


    }



}
