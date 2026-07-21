package com.finedge.finedge.Service;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;

import java.util.List;

public interface TransactionService {



    List<Razorpay_payment> getUserTranscation(User user);

    Integer getTranscationAmountByUser(User user);
}
