package com.example.pulsedesk.repository;

import com.example.pulsedesk.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
