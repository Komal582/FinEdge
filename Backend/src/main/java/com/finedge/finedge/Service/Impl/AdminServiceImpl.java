package com.finedge.finedge.Service.Impl;


import com.finedge.finedge.Model.*;
import com.finedge.finedge.Repository.*;
import com.finedge.finedge.Service.AdminService;
import com.finedge.finedge.Service.BalanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Razorpay_payment> getAllTranscation(){

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
    public long getTotalTranscations(){
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

}
