package com.finedge.finedge.Service;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;

public interface ExpenseService {
    Boolean addExpense(Expense expense);



    Integer getExpenseAmountByUser(User user);
}
