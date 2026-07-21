package com.finedge.finedge.Service.Impl;


import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.IncomeRepository;
import com.finedge.finedge.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public boolean saveIncome(Income income){



        incomeRepository.save(income);
        System.out.println(income.getIncome_id());
        System.out.println(income.getAmount());

        return true;
    }

    @Override
    public Integer getIncomeAmountByUser(User user){

        Optional<Integer> income = incomeRepository.getIncomeAmountByUser(user);
        System.out.println("Repository Response: " + income);

        return income.orElseThrow(() -> new RuntimeException("Income not found"));
    }
}
