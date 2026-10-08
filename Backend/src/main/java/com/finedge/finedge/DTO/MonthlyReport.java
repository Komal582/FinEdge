package com.finedge.finedge.DTO;

public class MonthlyReport {
    private String month;
    private Long income;
    private Long expense;
    private Long payment;
    private Long savings;

    public MonthlyReport(String month, Long income, Long expense, Long payment, Long savings){
         this.month = month;
         this.income=income;
         this.expense=expense;
         this.payment = payment;
         this.savings=savings;
    }


    public String getMonth(){
        return month;
    }

     public Long getIncome(){
        return income;
    }

     public Long getExpense(){
        return expense;
    }

     public Long getPayment(){
        return payment;
    }

    public Long getSavings(){
        return savings;
    }
    
}
