package com.finedge.finedge.Repository;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Razorpay_paymentRepository extends JpaRepository<Razorpay_payment,String> {


    List<Razorpay_payment> getByUser(User user);


    @Query("select sum(r.amount) from Razorpay_payment r where r.user=:user")
    Optional<Integer> getRazorpayPaymentAmountByUser(@Param("user") User user);


}
