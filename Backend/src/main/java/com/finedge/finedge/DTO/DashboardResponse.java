package com.finedge.finedge.DTO;

import java.util.List;

import com.finedge.finedge.Model.Razorpay_payment;

public class DashboardResponse {
    private String userName;
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

     public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


     public DashboardResponse() {
    }

     public DashboardResponse(String userName,
                             double balance,
                             double income,
                             double expense,
                            List<Razorpay_payment> transcationList
                        
                            ) {
        this.userName = userName;
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.transcationList=transcationList;
       
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
