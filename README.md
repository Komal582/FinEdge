#FinTrack - Personal Finance Tracking system
FinTrack is a Spring Boot-based personal finance tracking application that helps users record, categorize and analyze their income and expenses.
The application focuses on financial traking and reporting rather than peer-to-peer money transfer.

#Features
-User authentication using Sprig Security
-Income and expense management
-Dashboard with financial summaries
-Online transcation catpure using razorpay(test mode)
-Secure session handling
-Clean MVC architecture

#Tech Stack
-Java 
-Spring Boot
-Spring Security 
-Spring Data JPA
-Thymeleaf
-MySql
-Razorpay API 
-Maven 

#Razorpay Integration

Razorpay is integrated only to demonstrate secure online payment processing and callback handling.
Payments are collected by the application's merchant account in test mode and are used to record verified transcation events.
This project does not implement peer-to-peer money transfer or wallet functionality.

#Configuration & Setup

Before running the project, configure the following:
1.Create a MySql database named 'fintrack'
2.Update your local 'application.properties' file with:
  -Databse username and password
  -Razorpay API key and secret(test keys)

#How to Run the project
1.Clone the Repository:
git clone https://github.com/Komal582/Fintrack.git
2.Open the project in IDE
3.Configure application.properties 
4.Run the Spring Boot application
5.Open in browser:
   http://localhost:8080

#Scope and Limitations
-This application is designed for financial tracking and analysis
-It is not a banking or money transfer system
-Razorpay runs in test mode only
-Real peer-to-peer transfer are outside the scope of this project


