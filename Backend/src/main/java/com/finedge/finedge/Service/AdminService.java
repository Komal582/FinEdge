package com.finedge.finedge.Service;

import java.util.List;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;


public interface AdminService {

    List<User> getAllUser();



    List<Razorpay_payment> getAllTransaction();

    List<Balance> getAllBalance();

    List<Income> getAllIncome();

    List<Expense> getAllExpense();

    long getTotalUser();

    long getTotalTransactions();

    long getTotalIncome();

    long getTotalExpense();

    List<User> getLatestUser();

    List<Razorpay_payment> getLatestTransaction();

}
