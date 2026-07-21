package com.finedge.finedge.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RazorPayConfig {
    public String getKEY_ID() {
        return KEY_ID;
    }

    public String getKEY_SECRET() {
        return KEY_SECRET;
    }

    @Value("${razorpay.key.id}")
    private String KEY_ID;

    @Value("${razorpay.key.secret}")
    private String KEY_SECRET;

}







