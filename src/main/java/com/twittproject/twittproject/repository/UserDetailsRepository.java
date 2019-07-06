package com.twittproject.twittproject.repository;

import com.twittproject.twittproject.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Long countByEmail(String email);
}
