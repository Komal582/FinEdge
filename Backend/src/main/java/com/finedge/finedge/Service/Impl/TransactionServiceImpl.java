package com.finedge.finedge.Service.Impl;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.Razorpay_paymentRepository;
import com.finedge.finedge.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {




    @Autowired
    private Razorpay_paymentRepository razorpayPaymentRepository;


    @Override
    public List<Razorpay_payment> getUserTranscation(User user){

        return razorpayPaymentRepository.getByUser(user);
    }

    @Override
    public Integer getTranscationAmountByUser(User user){

        Optional<Integer> amount = razorpayPaymentRepository.getRazorpayPaymentAmountByUser(user);

        return amount.orElseThrow(()->new RuntimeException("Transcation amount not found"));
    }



}
