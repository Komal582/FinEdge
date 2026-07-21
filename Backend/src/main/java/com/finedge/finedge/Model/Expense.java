package com.finedge.finedge.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long expense_id;

    private Integer amount;
    private String category;
    private String note;
    private LocalDate date;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
    public Long getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(Long expense_id) {
        this.expense_id = expense_id;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
