package com.twittproject.twittproject.repository;

import com.twittproject.twittproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegisterUserRepository extends JpaRepository<User, Long> {
}
