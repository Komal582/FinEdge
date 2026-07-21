package com.finedge.finedge.Service;

import com.finedge.finedge.Model.User;
import com.razorpay.RazorpayException;

import java.util.Map;

public interface PaymentService {
    Map<String,Object> createOrder(Integer amount) throws RazorpayException;

    Boolean savePaymentDetails(String payment_id, User user) throws RazorpayException;
}
