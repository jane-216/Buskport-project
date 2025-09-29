package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
