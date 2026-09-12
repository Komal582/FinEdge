package com.finedge.finedge.Service.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.Razorpay_paymentRepository;
import com.finedge.finedge.Service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {




    @Autowired
    private Razorpay_paymentRepository razorpayPaymentRepository;

    @Override
     public List<Razorpay_payment> getLatestUserTranscation(Long UserId){
        Pageable topFive = PageRequest.of(0, 5);
       

        List<Razorpay_payment> payments = razorpayPaymentRepository.findPaymentsByUserId(UserId, topFive);
          System.out.println("PAYMENTS RETURNED: " + payments.size());
        return payments;
     }


    @Override
    public List<Razorpay_payment> getUserTranscation(User user){

        return razorpayPaymentRepository.getByUser(user);
    }

    @Override
    public Integer getTranscationAmountByUser(User user){

        Optional<Integer> amount = razorpayPaymentRepository.getRazorpayPaymentAmountByUser(user);

        return amount.orElseThrow(()->new RuntimeException("Transcation amount not found"));
    }

    private Pageable PageRequest(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
