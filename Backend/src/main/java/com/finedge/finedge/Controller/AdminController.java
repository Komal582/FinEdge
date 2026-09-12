package com.finedge.finedge.Controller;

import org.springframework.ui.Model;

import com.finedge.finedge.Model.*;
import com.finedge.finedge.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model){

        long total_users =adminService.getTotalUser();
        long total_transcations=adminService.getTotalTranscations();
        long total_income=adminService.getTotalIncome();
        long total_expense=adminService.getTotalExpense();

        model.addAttribute("total_users",total_users);
        model.addAttribute("total_transcations" , total_transcations);
        model.addAttribute("total_income" , total_income);
        model.addAttribute("total_expense" , total_expense);


        return "admin_dashboard";
    }

    @GetMapping("/login")
    public String loginUserPage(){
        return "Login";
    }

    @GetMapping("/userlist")
    public ResponseEntity<String> userlist() {

        List<User> users = adminService.getAllUser();

        StringBuilder html = new StringBuilder();

        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Admin - User List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 900px;
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
            <h2>Registered Users</h2>

            <table>
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
        """);

        for (User u : users) {
            html.append("<tr>");
            html.append("<td>").append(u.getUser_id()).append("</td>");
            html.append("<td>").append(u.getUsername()).append("</td>");
            html.append("<td>").append(u.getEmail()).append("</td>");
            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/admin/dashboard" class="back-btn">⬅ Back to Admin Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }

    @GetMapping("/transcations")
    public ResponseEntity<String> transcation(){

        List<Razorpay_payment> transcation = adminService.getAllTranscation();
        StringBuilder html= new StringBuilder();
        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Admin - User List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 900px;
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
            <h2>Registered Users</h2>

            <table>
                <tr>
                    <th>Order ID</th>
                    <th>Payment ID</th>
                    <th>Amount</th>
                    <th>Currency</th>
                    <th>Method</th>
                    <th>Date</th>
                </tr>
        """);

        for (Razorpay_payment trans : transcation) {
            html.append("<tr>");
            html.append("<td>").append(trans.getOrder_id()).append("</td>");
            html.append("<td>").append(trans.getPayment_id()).append("</td>");
            html.append("<td>").append(trans.getAmount()).append("</td>");
            html.append("<td>").append(trans.getCurrency()).append("</td>");
            html.append("<td>").append(trans.getMethod()).append("</td>");
            html.append("<td>").append(trans.getCreatedAt()).append("</td>");





            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/admin/dashboard" class="back-btn">⬅ Back to Admin Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }

    @GetMapping("/balance")
    public ResponseEntity<String> balance(){

        List<Balance> balance = adminService.getAllBalance();
        StringBuilder html= new StringBuilder();
        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Admin - User List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 900px;
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
            <h2>Registered Users</h2>

            <table>
                <tr>
                    <th>Balance ID</th>
                    <th>Name</th>
                    <th>Balance</th>
                    <th>Date</th>
                    
                </tr>
        """);

        for (Balance bal: balance) {
            html.append("<tr>");
            html.append("<td>").append(bal.getBalance_id()).append("</td>");
            html.append("<td>").append(bal.getUser().getUsername()).append("</td>");
            html.append("<td>").append(bal.getCurrent_balance()).append("</td>");
            html.append("<td>").append(bal.getLast_updated()).append("</td>");






            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/admin/dashboard" class="back-btn">⬅ Back to Admin Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }

    @GetMapping("/income")
    public ResponseEntity<String> income(){

        List<Income> income = adminService.getAllIncome();
        StringBuilder html= new StringBuilder();
        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Admin - User List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 900px;
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
            <h2>Registered Users</h2>

            <table>
                <tr>
                    <th>Balance ID</th>
                    <th>Name</th>
                    <th>Amount</th>
                    <th>Date</th>
                     <th>Source</th>
                </tr>
        """);

        for (Income i : income) {
            html.append("<tr>");
            html.append("<td>").append(i.getIncome_id()).append("</td>");
            html.append("<td>").append(i.getUser().getUsername()).append("</td>");
            html.append("<td>").append(i.getAmount()).append("</td>");
            html.append("<td>").append(i.getDate()).append("</td>");
            html.append("<td>").append(i.getSource()).append("</td>");





            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/admin/dashboard" class="back-btn">⬅ Back to Admin Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }
    @GetMapping("/expense")
    public ResponseEntity<String> expense(){

        List<Expense> expense = adminService.getAllExpense();
        StringBuilder html= new StringBuilder();
        html.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Admin - User List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 900px;
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
            <h2>Registered Users</h2>

            <table>
                <tr>
                    <th>Expense ID</th>
                    <th>User ID</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Note</th>
                    <th>Category</th>
                </tr>
        """);

        for (Expense exp : expense) {
            html.append("<tr>");
            html.append("<td>").append(exp.getExpense_id()).append("</td>");
            html.append("<td>").append(exp.getUser().getUsername()).append("</td>");
            html.append("<td>").append(exp.getAmount()).append("</td>");
            html.append("<td>").append(exp.getDate()).append("</td>");
            html.append("<td>").append(exp.getNote()).append("</td>");
            html.append("<td>").append(exp.getCategory()).append("</td>");






            html.append("</tr>");
        }

        html.append("""
            </table>

            <a href="/admin/dashboard" class="back-btn">⬅ Back to Admin Dashboard</a>
        </div>

        </body>
        </html>
        """);

        return ResponseEntity.ok(html.toString());
    }

}
