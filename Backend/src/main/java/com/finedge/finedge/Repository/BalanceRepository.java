package com.finedge.finedge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.User;

public interface BalanceRepository extends JpaRepository<Balance,Long> {



   Balance findByUser(User user);

}
