package com.finedge.finedge.Service.Impl;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.Razorpay_paymentRepository;
import com.finedge.finedge.Service.PaymentService;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private Razorpay_paymentRepository razorpayPaymentRepository;


   private final RazorpayClient razorpayClient;

    public PaymentServiceImpl(RazorpayClient razorpayClient){

        this.razorpayClient=razorpayClient;
    }

    @Override
    public Map<String,Object> createOrder(Integer amount) throws RazorpayException {


        JSONObject orderRequest =new JSONObject();
        orderRequest.put("amount",amount);
        orderRequest.put("currency","INR");

        Order order =razorpayClient.orders.create(orderRequest);

        Map<String, Object> order_data = new HashMap<>();
        order_data.put("order_id",order.get("id"));
        order_data.put("currency",order.get("currency"));
        order_data.put("amount",order.get("amount"));
        return order_data;
    }

    @Override
    public Boolean savePaymentDetails(String payment_id, User user) throws RazorpayException {

        Payment payment=razorpayClient.payments.fetch(payment_id);


        Razorpay_payment razorpayPayment= new Razorpay_payment();
        razorpayPayment.setPayment_id(payment.get("id"));
        razorpayPayment.setCurrency(payment.get("currency"));
        razorpayPayment.setPayment_status(payment.get("status"));
        razorpayPayment.setMethod(payment.get("method"));
        razorpayPayment.setAmount(payment.get("amount"));
        razorpayPayment.setCreated_at(payment.get("created_at"));
        razorpayPayment.setOrder_id(payment.get("order_id"));
        razorpayPayment.setUser(user);

        razorpayPaymentRepository.save(razorpayPayment);

        return true;



    }
}
