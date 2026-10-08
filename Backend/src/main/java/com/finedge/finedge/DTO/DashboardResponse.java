package com.finedge.finedge.DTO;

import java.util.List;

import com.finedge.finedge.Model.Razorpay_payment;

public class DashboardResponse {
    private String username;
    private String email;

    public String getEmail() {
        return email;
    }

  

    private double expense;
    private double income;
    private double balance;
    private List<Razorpay_payment> transcationList;

     public List<Razorpay_payment> getTranscationList() {
        return transcationList;
    }

    public void setTranscationList(List<Razorpay_payment> transcationList) {
        this.transcationList = transcationList;
    }

     public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }


     public DashboardResponse() {
    }

     public DashboardResponse(String username,
                             double balance,
                             double income,
                             double expense,
                             String email,
                            List<Razorpay_payment> transcationList
                        
                            ) {
        this.username = username;
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.transcationList=transcationList;
        this.email=email;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }
}
