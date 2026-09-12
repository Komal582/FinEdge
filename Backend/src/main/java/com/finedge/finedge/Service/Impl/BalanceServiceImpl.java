package com.finedge.finedge.Service.Impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.BalanceRepository;
import com.finedge.finedge.Service.BalanceService;
import com.finedge.finedge.Service.ExpenseService;
import com.finedge.finedge.Service.IncomeService;
import com.finedge.finedge.Service.TransactionService;

@Service
public class BalanceServiceImpl implements BalanceService {

   

     private final ExpenseService expenseService;
     private final IncomeService incomeService;
     private final TransactionService transactionService;
     private final BalanceRepository balanceRepository;

     public BalanceServiceImpl(TransactionService transactionService, ExpenseService expenseService,
               IncomeService incomeService, BalanceRepository balanceRepository) {
          this.expenseService = expenseService;
          this.incomeService = incomeService;
          this.transactionService = transactionService;
          this.balanceRepository = balanceRepository;
     }

     @Override
     public Integer getBalanceById(User user) {

          Integer income_amount = incomeService.getIncomeAmountByUser(user);
          
          Integer expense_amount = expenseService.getExpenseAmountByUser(user);

          Integer transcation_amount = transactionService.getTranscationAmountByUser(user);

          if (income_amount == null) {
               income_amount = 0;
          }
          if (expense_amount == null) {
               expense_amount = 0;
          }
          if (transcation_amount == null) {
               transcation_amount = 0;
          }
          Integer total_balance = income_amount - (expense_amount + transcation_amount);
          Balance balance = new Balance();

          // Boolean flag = findUser(user);

          // if (flag == false) {

          //      balance.setUser(user);
          //      balance.setCurrent_balance(total_balance);
          //      balance.setLast_updated(LocalDateTime.now());

          //      saveBalance(balance);

          // } else {
          //      balance = getBalanceByUser(user);
          //      balance.setCurrent_balance(total_balance);
          //      updateBalance(balance);

          // }

          return total_balance;

     }

     @Override
     public Boolean saveBalance(Balance balance) {
          balanceRepository.save(balance);
          return true;
     }

     @Override
     public Boolean updateBalance(Balance balance) {
          balanceRepository.save(balance);
          return true;
     }

     @Override
     public Balance getBalanceByUser(User user) {

          Balance balance = balanceRepository.findByUser(user);

          return balance;

     }

     @Override
     public boolean findUser(User user) {

          Balance balance = balanceRepository.findByUser(user);

          if (balance == null) {
               return false;
          } else {
               return true;
          }
     }

}
