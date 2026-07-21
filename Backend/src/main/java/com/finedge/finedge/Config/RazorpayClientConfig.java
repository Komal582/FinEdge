package com.finedge.finedge.Config;


import com.finedge.finedge.Component.RazorPayConfig;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfig {




    @Bean
     public RazorpayClient razorpayClient(RazorPayConfig razorPayConfig) throws RazorpayException {
         return new RazorpayClient(razorPayConfig.getKEY_ID(),razorPayConfig.getKEY_SECRET());
     }



}
