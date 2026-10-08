package com.finedge.finedge.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finedge.finedge.Model.Expense;
import com.finedge.finedge.Model.User;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("select sum(e.amount) from Expense e where e.user=:user")
   Integer getExpenseAmountByUser(@Param("user") User user);

    public List<Expense> getByUser(User user);

    @Query("select sum(e.amount) from Expense e where e.user=:user and e.date>=:startDate and e.date<:endDate")
    Optional<Long> getTotalExpenseByDateRange(
        @Param("user") User user,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );

    @Query("select sum(e.amount) from Expense e where e.user=:user and e.category=:category and e.date>=:startDate and e.date<=:endDate")
    Optional<Long> getTotalExpenseByCategory(
        @Param("user") User user,
        @Param("category") String category,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );

}
