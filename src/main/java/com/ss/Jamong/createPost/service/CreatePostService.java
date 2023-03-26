package com.ss.Jamong.createPost.service;

import com.ss.Jamong.createPost.repository.CreatePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    @Autowired
    private CreatePostRepository createPostRepository;

}
