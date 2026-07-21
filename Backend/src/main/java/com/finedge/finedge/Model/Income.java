package com.finedge.finedge.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Table(name="income")
public class Income {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long income_id;

    private Integer amount;
    private String source;
    private String note;
    private LocalDate date;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getIncome_id() {
        return income_id;
    }

    public void setIncome_id(Long income_id) {
        this.income_id = income_id;
    }


    public Integer getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
