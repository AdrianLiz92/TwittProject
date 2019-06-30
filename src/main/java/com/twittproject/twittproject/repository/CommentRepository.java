package com.twittproject.twittproject.repository;

import com.twittproject.twittproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentsByPost_idEquals(Long id);
    Optional<Comment> findCommentById(Long id);
}
