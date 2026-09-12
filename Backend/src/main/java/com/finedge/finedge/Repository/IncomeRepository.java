package com.finedge.finedge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;

public interface IncomeRepository extends JpaRepository<Income,Long> {



    @Query("select sum(i.amount) from Income i where i.user=:user")
    Integer getIncomeAmountByUser(@Param("user") User user);

    List<Income> getByUser(User user);
}
