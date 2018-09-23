package com.zhukdi.blog4you.controller;

import com.zhukdi.blog4you.entity.Post;
import com.zhukdi.blog4you.repository.IPostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dmitry on 9/23/2018.
 */
@RestController
@RequestMapping("posts")
public class PostController {
    private final IPostRepository postRepository;

    @Autowired
    public PostController(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable("id") Post post) {
        return post;
    }

    @PostMapping
    public Post addPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Post postFromDb, @RequestBody Post post) {
        BeanUtils.copyProperties(post, postFromDb, "id");
        return postRepository.save(postFromDb);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Post post) {
        postRepository.delete(post);
    }
}
