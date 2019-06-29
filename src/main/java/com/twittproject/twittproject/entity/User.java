package com.twittproject.twittproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails user_details;
    @Column(name = "lock_date")
    private Date lockDate;
    @Column(name = "unlock_date")
    private Date unlockDate;


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetails getUser_details() {
        return user_details;
    }

    public void setUser_details(UserDetails user_details) {
        this.user_details = user_details;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Date getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(Date unlockDate) {
        this.unlockDate = unlockDate;
    }
}


