      package com.finedge.finedge.Controller;

      import com.finedge.finedge.Model.*;
      import com.finedge.finedge.Repository.UserRepository;
      import com.finedge.finedge.Service.BalanceService;
      import com.finedge.finedge.Service.ExpenseService;
      import com.finedge.finedge.Service.IncomeService;
      import com.finedge.finedge.Service.TransactionService;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.http.ResponseEntity;
      import org.springframework.security.core.Authentication;
      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.GetMapping;
      import org.springframework.web.bind.annotation.RequestMapping;

      import java.time.LocalDateTime;

      @Controller
      @RequestMapping("/balance")
      public class BalanceController {


            @Autowired
            private BalanceService balanceService;

            @Autowired
            private ExpenseService expenseService;

            @Autowired
            private IncomeService incomeService;

            @Autowired
            private TransactionService transactionService;


            @Autowired
            private UserRepository userRepository;

            @GetMapping("/view")
            public ResponseEntity<?> ViewBalance(Authentication authentication) {

                  User user =(User)authentication.getPrincipal();


                  Balance balance1 = balanceService.getBalanceById(user);

                  if (balance1 == null) {
                        return ResponseEntity.ok("<h3>No balance data found!</h3>");
                  }

                  StringBuilder sr = new StringBuilder();

                  sr.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>FinTrack | Balance</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f7fb;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    width: 90%;
                    max-width: 600px;
                    margin: 60px auto;
                    background: #ffffff;
                    padding: 30px;
                    border-radius: 12px;
                    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                    text-align: center;
                }

                h2 {
                    color: #1e3a8a;
                    margin-bottom: 20px;
                }

                .balance {
                    font-size: 32px;
                    font-weight: bold;
                    color: #16a34a;
                    margin: 15px 0;
                }

                .updated {
                    color: #6b7280;
                    margin-bottom: 25px;
                }

                .btn {
                    display: inline-block;
                    padding: 10px 18px;
                    background-color: #1e3a8a;
                    color: white;
                    text-decoration: none;
                    border-radius: 6px;
                }

                .btn:hover {
                    background-color: #163172;
                }
            </style>
        </head>

        <body>

        <div class="container">
            <h2>Your Current Balance</h2>

            <div class="balance">
                ₹ """ + balance1.getCurrent_balance() + """
            </div>

            <div class="updated">
                Last Updated: """ + balance1.getLast_updated() + """
            </div>

            <a href="/user/dashboard" class="btn">⬅ Back to Dashboard</a>
        </div>

        </body>
        </html>
        """);

                  return ResponseEntity.ok(sr.toString());
            }



            @GetMapping("/updateBalance")
            public String updateBalance(Authentication authentication){

                  User user = (User) authentication.getPrincipal();



                  Integer income_amount =incomeService.getIncomeAmountByUser(user);
                  Integer expense_amount= expenseService.getExpenseAmountByUser (user);
                  Integer transcation_amount = transactionService.getTranscationAmountByUser(user);


                  Integer total_balance=income_amount-(expense_amount + transcation_amount);
                  Balance balance = new Balance();

                  Boolean flag =balanceService.findUser(user);


                  if(flag==false) {


                        balance.setUser(user);
                        balance.setCurrent_balance(total_balance);
                        balance.setLast_updated(LocalDateTime.now());


                        balanceService.saveBalance(balance);


                  }
                  else{
                        balance = balanceService.getBalanceByUser(user);
                        balance.setCurrent_balance(total_balance);
                        balanceService.updateBalance(balance);


                  }

                  return "redirect:/balance/view";

            }



      }
