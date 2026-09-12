package com.finedge.finedge.Service;

import java.util.List;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;


public interface IncomeService {
    boolean saveIncome(Income income);

    Integer getIncomeAmountByUser(User user);

    public List<Income> getIncomeHistory(User user);
}
