package com.finedge.finedge.Repository;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("select sum(e.amount) from Expense e where e.user=:user")
    Optional<Integer> getExpenseAmountByUser(@Param("user") User user);


}
