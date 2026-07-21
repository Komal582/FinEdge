package com.finedge.finedge.Controller;


import com.finedge.finedge.Component.RazorPayConfig;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.PaymentService;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import com.razorpay.Utils;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private RazorPayConfig razorPayConfig;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public String PaymentPage(){
        return "userPayment";
    }

    @ResponseBody
    @PostMapping("/create-order")
    public Map<String,Object> createOrder(@RequestParam("amount") Integer amount) throws RazorpayException {



        System.out.println("Create-order");
        Map<String,Object> map=paymentService.createOrder(amount);
        System.out.print(map);
        return map;
    }

    @ResponseBody
    @PostMapping("/payment-callback")
    public Map<String,Object> paymentCallback(@RequestBody Map<String,Object> data , Authentication authentication)throws RazorpayException{

        User user =(User) authentication.getPrincipal();

        try{

            String order_id=(String)data.get("razorpay_order_id");
            String payment_id=(String)data.get("razorpay_payment_id");
            String signature=(String)data.get("razorpay_signature");



            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", order_id);
            options.put("razorpay_payment_id", payment_id);
            options.put("razorpay_signature", signature);


            boolean isValid =  Utils.verifyPaymentSignature(options, razorPayConfig.getKEY_SECRET());

           if(isValid){
              Boolean value= paymentService.savePaymentDetails(payment_id,user);
               if(value==false){
                   throw new RuntimeException("Values not saved");
               }
           }




            Map<String,Object> map=new HashMap<>();
            map.put("success",isValid);

            return map;


        }
        catch(RazorpayException e){
            System.err.println("Razorpay Exception during callback:" +e.getMessage());
            throw e;
        }
        catch(Exception e){
            System.err.println("General Exception during callback:"+e.getMessage());
            throw new RazorpayException("General exception during callback");
        }

    }

}
