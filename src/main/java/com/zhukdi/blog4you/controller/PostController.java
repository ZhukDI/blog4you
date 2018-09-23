package com.zhukdi.blog4you.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dmitry on 9/23/2018.
 */
@RestController
@RequestMapping("post")
public class PostController {

    @GetMapping
    public String list() {
        return "index";
    }
}
