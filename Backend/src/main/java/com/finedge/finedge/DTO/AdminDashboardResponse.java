package com.finedge.finedge.DTO;

import java.util.List;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;

public class AdminDashboardResponse {

    long total_users;
    long total_transactions;
    long total_income;
    long total_expense;
    List<User> userList;
    List<Razorpay_payment> transactionList;
    String username;
    String email;

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Razorpay_payment> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Razorpay_payment> transactionList) {
        this.transactionList = transactionList;
    }

    public long getTotal_users() {
        return total_users;
    }

    public long getTotal_transactions() {
        return total_transactions;
    }

    public long getTotal_income() {
        return total_income;
    }

    public long getTotal_expense() {
        return total_expense;
    }

    public AdminDashboardResponse(String username,String email,long total_users, long total_transactions, long total_income,long total_expense,List<User> userList,List<Razorpay_payment> transactionList ) {
        this.total_users = total_users;
        this.total_transactions = total_transactions;
        this.total_income = total_income;
        this.total_expense = total_expense;
        this.transactionList=transactionList;
        this.userList=userList;
        this.email= email;
        this.username= username;
    }

    public void setTotal_users(long total_users) {
        this.total_users = total_users;
    }

    public void setTotal_transactions(long total_transactions) {
        this.total_transactions = total_transactions;
    }

    public void setTotal_income(long total_income) {
        this.total_income = total_income;
    }

    public void setTotal_expense(long total_expense) {
        this.total_expense = total_expense;
    }

}
