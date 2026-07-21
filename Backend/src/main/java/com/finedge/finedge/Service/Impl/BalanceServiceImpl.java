package com.finedge.finedge.Service.Impl;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.BalanceRepository;
import com.finedge.finedge.Service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {


     @Autowired
     BalanceRepository balanceRepository;

     @Override
     public Balance getBalanceById(User user){

          Optional<Balance> balance =balanceRepository.findByUser(user);

          return balance.orElseThrow(()->new RuntimeException("Balance Not Found while viewing"));
     }

     @Override
     public Boolean saveBalance(Balance balance){
          balanceRepository.save(balance);
          return true;
     }

     @Override
     public Boolean  updateBalance(Balance balance){
          balanceRepository.save(balance);
          return true;
     }

     @Override
     public Balance getBalanceByUser(User user){

          Optional<Balance> balance =balanceRepository.findByUser(user);

          return balance.orElseThrow(()->new RuntimeException("Balance Not Found while updating"));


     }

     @Override
     public boolean findUser(User user){

          Optional<Balance> balance =balanceRepository.findByUser(user);

          if(balance.isEmpty()){
               return false;
          }
          else{
               return true;
          }
     }


}
