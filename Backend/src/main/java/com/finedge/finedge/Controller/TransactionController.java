package com.finedge.finedge.Controller;


import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transcation")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/history")
    public ResponseEntity<String> getUserTranscation(Authentication authentication) {

        User user =(User)authentication.getPrincipal();


        List<Razorpay_payment> userTranscationList =
                transactionService.getUserTranscation(user);

        StringBuilder html = new StringBuilder();

        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Transaction History</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 1000px;
                    margin: 40px auto;
                    background: #ffffff;
                    padding: 20px;
                    border-radius: 10px;
                    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                }

                h2 {
                    text-align: center;
                    color: #1e3a8a;
                    margin-bottom: 20px;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th, td {
                    padding: 12px;
                    text-align: center;
                    border-bottom: 1px solid #ddd;
                }

                th {
                    background-color: #1e3a8a;
                    color: white;
                }

                tr:nth-child(even) {
                    background-color: #f1f5f9;
                }

                .status-success {
                    color: green;
                    font-weight: bold;
                }

                .back-btn {
                    display: inline-block;
                    margin-top: 20px;
                    padding: 10px 16px;
                    background-color: #1e3a8a;
                    color: white;
                    text-decoration: none;
                    border-radius: 6px;
                }

                .back-btn:hover {
                    background-color: #163172;
                }
            </style>
        </head>
        <body>

        <div class="container">
            <h2>Transaction History</h2>

            <table>
                <tr>
                    <th>Payment ID</th>
                    <th>Order ID</th>
                    <th>Amount</th>
                    <th>Currency</th>
                    <th>Status</th>
                    <th>Method</th>
                    <th>Date</th>
                </tr>
        """);

        for (Razorpay_payment p : userTranscationList) {
            html.append("<tr>");
            html.append("<td>").append(p.getPayment_id()).append("</td>");
            html.append("<td>").append(p.getOrder_id()).append("</td>");
            html.append("<td>₹ ").append(p.getAmount()).append("</td>");
            html.append("<td>").append(p.getCurrency()).append("</td>");
            html.append("<td class='status-success'>")
                    .append(p.getPayment_status())
                    .append("</td>");
            html.append("<td>").append(p.getMethod()).append("</td>");
            html.append("<td>").append(p.getCreated_at()).append("</td>");
            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/user/dashboard" class="back-btn">⬅ Back to Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }

}
