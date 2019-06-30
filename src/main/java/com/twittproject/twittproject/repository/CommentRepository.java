package com.twittproject.twittproject.repository;

import com.twittproject.twittproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentsByPost_idEquals(Long id);
}
