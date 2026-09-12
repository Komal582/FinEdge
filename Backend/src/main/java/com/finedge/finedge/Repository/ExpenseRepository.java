package com.finedge.finedge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("select sum(e.amount) from Expense e where e.user=:user")
   Integer getExpenseAmountByUser(@Param("user") User user);

    public List<Expense> getByUser(User user);


}
