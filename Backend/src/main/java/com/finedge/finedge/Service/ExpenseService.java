package com.finedge.finedge.Service;

import java.util.List;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;

public interface ExpenseService {
    boolean addExpense(Expense expense);



    Integer getExpenseAmountByUser(User user);

    public List<Expense> getExpenseHistory(User user);
}
