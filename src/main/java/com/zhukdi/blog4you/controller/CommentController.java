package com.zhukdi.blog4you.controller;

import com.zhukdi.blog4you.entity.Comment;
import com.zhukdi.blog4you.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dmitry on 9/26/2018.
 */
@RestController
public class CommentController {
    private final ICommentRepository commentRepository;

    @Autowired
    public CommentController(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }
}
