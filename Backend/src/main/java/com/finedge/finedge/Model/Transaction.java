package com.finedge.finedge.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
public class Transaction {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long transaction_id ;

        @OneToOne
        @JoinColumn(name="user_id")
        private User user;

        private String type;
        private Integer amount;
        private String paymentmethod;
        private String reference_id;
        private LocalDateTime time=LocalDateTime.now();
        private String status;


    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
