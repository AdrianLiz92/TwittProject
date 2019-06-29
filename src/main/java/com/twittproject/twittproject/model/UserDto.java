package com.twittproject.twittproject.model;

import java.sql.Timestamp;

public class UserDto {
    private String login;
    private String password;
    private String role;
    private Long user_details_id;
    private Timestamp lock_date;
    private Timestamp unlock_date;

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

    public Long getUser_details_id() {
        return user_details_id;
    }

    public void setUser_details_id(Long user_details_id) {
        this.user_details_id = user_details_id;
    }

    public Timestamp getLock_date() {
        return lock_date;
    }

    public void setLock_date(Timestamp lock_date) {
        this.lock_date = lock_date;
    }

    public Timestamp getUnlock_date() {
        return unlock_date;
    }

    public void setUnlock_date(Timestamp unlock_date) {
        this.unlock_date = unlock_date;
    }
}
