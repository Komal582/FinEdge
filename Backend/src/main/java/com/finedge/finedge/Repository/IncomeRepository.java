package com.finedge.finedge.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finedge.finedge.Model.Income;
import com.finedge.finedge.Model.User;

public interface IncomeRepository extends JpaRepository<Income,Long> {



    @Query("select sum(i.amount) from Income i where i.user=:user")
    Integer getIncomeAmountByUser(@Param("user") User user);

    List<Income> getByUser(User user);

    @Query("select sum(i.amount) from Income i where i.user=:user and i.date>=:startDate and i.date<:endDate")
    Optional<Long> getTotalIncomeByDateRange(
        @Param("user")User user,
        @Param("startDate")LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
}
