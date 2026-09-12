package com.finedge.finedge.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.TransactionService;

@RestController
@RequestMapping("/transcation")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/history")
    public List<Razorpay_payment> getUserTranscation(Authentication authentication) {
       User user = (User)authentication.getPrincipal();

         List<Razorpay_payment> userTranscationList =
                transactionService.getUserTranscation(user);
        return userTranscationList;

      
    }

}
