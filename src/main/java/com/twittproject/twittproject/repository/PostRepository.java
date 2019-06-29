package com.twittproject.twittproject.repository;

import com.twittproject.twittproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;




public interface PostRepository extends JpaRepository<Post, Long> {

}
