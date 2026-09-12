package com.finedge.finedge.Service;

import java.util.List;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;

public interface TransactionService {


    List<Razorpay_payment> getLatestUserTranscation(Long userID);
    List<Razorpay_payment> getUserTranscation(User user);

    Integer getTranscationAmountByUser(User user);
}
