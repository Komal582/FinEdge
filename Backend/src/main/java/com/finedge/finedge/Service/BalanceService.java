package com.finedge.finedge.Service;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.User;


public interface BalanceService {



    Balance getBalanceById(User user);


    Boolean saveBalance(Balance balance);

    Boolean  updateBalance(Balance balance);

    Balance getBalanceByUser(User user);

    boolean findUser(User user);




}
