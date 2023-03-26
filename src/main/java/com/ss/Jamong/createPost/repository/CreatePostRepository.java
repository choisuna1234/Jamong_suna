package com.ss.Jamong.createPost.repository;

import com.ss.Jamong.createPost.entity.CreatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatePostRepository extends JpaRepository<CreatePost, Long>{

}
