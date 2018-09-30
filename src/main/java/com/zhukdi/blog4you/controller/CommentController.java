package com.zhukdi.blog4you.controller;

import com.zhukdi.blog4you.entity.Comment;
import com.zhukdi.blog4you.exception.NotFoundException;
import com.zhukdi.blog4you.repository.ICommentRepository;
import com.zhukdi.blog4you.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Dmitry on 9/26/2018.
 */
@RestController
public class CommentController {
    private final ICommentRepository commentRepository;

    private final IPostRepository postRepository;

    @Autowired
    public CommentController(ICommentRepository commentRepository, IPostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(
            @PathVariable(value = "postId") Long postId,
            Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(
            @PathVariable(value = "postId") Long postId,
            @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new NotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new NotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new NotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable (value = "postId") Long postId,
            @PathVariable (value = "commentId") Long commentId) {
        if (!postRepository.existsById(postId)) {
            throw new NotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("CommentId " + commentId + " not found"));
    }
}
