package com.finedge.finedge.Service;

import com.finedge.finedge.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {

    List<User> getAllUser();



    List<Razorpay_payment> getAllTranscation();

    List<Balance> getAllBalance();

    List<Income> getAllIncome();

    List<Expense> getAllExpense();

    long getTotalUser();

    long getTotalTranscations();

    long getTotalIncome();

    long getTotalExpense();
}
