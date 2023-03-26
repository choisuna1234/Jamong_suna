package com.ss.Jamong.createPost.controller;

import com.ss.Jamong.createPost.service.CreatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class CreatePostController {

    @Autowired
    private CreatePostService createPostService;

    @GetMapping("/write")
    public String boardNoticeWriteForm(Model model) {
        return "/createPost/write";
    }

}
