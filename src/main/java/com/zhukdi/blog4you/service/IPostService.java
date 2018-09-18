package com.zhukdi.blog4you.service;

import com.zhukdi.blog4you.entity.Post;

import java.util.List;

/**
 * Created by Dmitry on 9/17/2018.
 */
public interface IPostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Long addPost(Post post);
    void updatePost(Post post);
    void deletePost(Post post);
}
