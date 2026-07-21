package com.finedge.finedge.Repository;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income,Long> {



    @Query("select sum(i.amount) from Income i where i.user=:user")
    Optional<Integer> getIncomeAmountByUser(@Param("user") User user);
}
