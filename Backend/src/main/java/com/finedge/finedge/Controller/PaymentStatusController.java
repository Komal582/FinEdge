package com.finedge.finedge.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paymentStatus")
public class PaymentStatusController {

    @GetMapping("/success")
    public String Success(){
        return "userPaymentSuccess";
    }

    @GetMapping("/failure")
    public String Failure(){
        return "userPaymentFailure";
    }
}
