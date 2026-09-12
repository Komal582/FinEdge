package com.finedge.finedge.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finedge.finedge.Model.Razorpay_payment;
import com.finedge.finedge.Model.User;

@Repository
public interface Razorpay_paymentRepository extends JpaRepository<Razorpay_payment,String> {

    
    List<Razorpay_payment> getByUser(User user);
    
    @Query("""
            select p from Razorpay_payment p where p.user.user_id=:userId
            order by p.createdAt desc
            """)
    List<Razorpay_payment> findPaymentsByUserId(@Param(value = "userId")
            Long userId, Pageable pageable);

    @Query("select sum(r.amount) from Razorpay_payment r where r.user=:user")
    Optional<Integer> getRazorpayPaymentAmountByUser(@Param("user") User user);


}
