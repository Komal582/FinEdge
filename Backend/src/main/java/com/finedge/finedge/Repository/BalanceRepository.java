package com.finedge.finedge.Repository;

import com.finedge.finedge.Model.Balance;
import com.finedge.finedge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance,Long> {



    Optional<Balance> findByUser(User user);








}
