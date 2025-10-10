package com.example.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	/**
     * 사용자가 작성한 게시물을 모두 조회한다.
     */
    List<Post> findByUser_UserId(Long userId);
    
    /**
     * 해당 사용자가 작성한 모든 게시물을 삭제한다.
     * @param userId
     * @return
     */
    int deleteByUser_UserId(Long userId);
    
    /**
     * 해당 사용자가 작성한 모든 게시물의 갯수를 구한다.
     * @param userId
     * @return
     */
    int countByUser_UserId(Long userId);
    
    
}
