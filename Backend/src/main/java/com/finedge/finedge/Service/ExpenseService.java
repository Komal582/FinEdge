package com.finedge.finedge.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;

public interface ExpenseService {
    boolean addExpense(Expense expense);

    HashMap<String,Long> getExpenseAmountByCategory(User user,Integer year, List<String> category);

    Integer getExpenseAmountByUser(User user);

    public List<Expense> getExpenseHistory(User user);
}
