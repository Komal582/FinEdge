package com.finedge.finedge.Model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
public class User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime created_at=LocalDateTime.now();
    private LocalDateTime updated_at= LocalDateTime.now();
    private String role;

    @OneToMany(mappedBy="user" , cascade=CascadeType.ALL)
    private List<Income> income;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
    private Balance balance;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Expense> expense;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Razorpay_payment> razorpay;


}
