package com.finedge.finedge.Service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.IncomeRepository;
import com.finedge.finedge.Service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public boolean saveIncome(Income income){



        incomeRepository.save(income);
       

        return true;
    }

    @Override
    public Integer getIncomeAmountByUser(User user){

        Integer income = incomeRepository.getIncomeAmountByUser(user);
        
        if(income!=null){
            return income;
        }
        else{
            return 0;
        }

        
    }

    @Override
    public List<Income> getIncomeHistory(User user){
        return incomeRepository.getByUser(user);
    }
}
