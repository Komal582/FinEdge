package com.finedge.finedge.Service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.AdminRepository;
import com.finedge.finedge.Repository.BalanceRepository;
import com.finedge.finedge.Repository.ExpenseRepository;
import com.finedge.finedge.Repository.IncomeRepository;
import com.finedge.finedge.Repository.Razorpay_paymentRepository;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.AdminService;

@Service()
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private Razorpay_paymentRepository razorpayPaymentRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public List<User> getAllUser(){

        List<User> user = userRepository.findAll();

        if(user.isEmpty()){
            throw new RuntimeException("Users not found");
        }
        else{
            return user;
        }


    }



    @Override
    public List<Razorpay_payment> getAllTransaction(){

        List<Razorpay_payment> transcation =razorpayPaymentRepository.findAll();

        if(transcation.isEmpty()){
            throw new RuntimeException("Transcations not found");
        }
        else{
            return transcation;
        }



    }

    @Override
    public List<Balance> getAllBalance(){
        List<Balance> balances= balanceRepository.findAll();
        if(balances.isEmpty()){
            throw new RuntimeException("Balance not found");
        }
        else{
            return balances;
        }

    }

    @Override
    public List<Income> getAllIncome(){
        List<Income> income= incomeRepository.findAll();
        if(income.isEmpty()){
            throw new RuntimeException("Income not found");
        }
        else{
            return income;
        }

    }

    @Override
    public List<Expense> getAllExpense(){
        List<Expense> expenses= expenseRepository.findAll();
        if(expenses.isEmpty()){
            throw new RuntimeException("Expenses not found");
        }
        else{
            return expenses;
        }

    }

    @Override
    public long getTotalUser(){
        return userRepository.count();
    }
    @Override
    public long getTotalTransactions(){
        return razorpayPaymentRepository.count();
    }
    @Override
    public long getTotalIncome(){
        return incomeRepository.count();
    }
    @Override
    public long getTotalExpense(){
        return expenseRepository.count();
    }

    @Override 
    public List<User> getLatestUser(){

       
        Pageable pageable = PageRequest.of(0,5);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> topFiveUsers =userPage.getContent();

        if(topFiveUsers.isEmpty()){
            throw new RuntimeException("Users not found");
        }
        else{
            return topFiveUsers;
        }
    }

    @Override
    public List<Razorpay_payment> getLatestTransaction(){
        Pageable pageable = PageRequest.of(0,5);
        Page<Razorpay_payment> page = razorpayPaymentRepository.findAll(pageable);
        System.out.println("Page"+ page);
        List<Razorpay_payment> topFiveTransactions=page.getContent();

         if(topFiveTransactions.isEmpty()){
            throw new RuntimeException("Transactions not found");
        }
        else{
            return topFiveTransactions;
        }

    }

}
