package com.finedge.finedge.Model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name="balance")
public class Balance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long balance_id;

    private Integer current_balance;
    private LocalDateTime last_updated= LocalDateTime.now();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(Long balance_id) {
        this.balance_id = balance_id;
    }

    public Integer getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Integer current_balance) {
        this.current_balance = current_balance;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
    }
}
