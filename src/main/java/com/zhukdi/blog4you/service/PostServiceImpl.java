package com.zhukdi.blog4you.service;

import com.zhukdi.blog4you.entity.Post;
import com.zhukdi.blog4you.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dmitry on 9/18/2018.
 */
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getPostById(Long id) {
        Optional<Post> posts = postRepository.findById(id);
//        Post post = postRepository.findById(id);
        return posts.orElse(null);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Long addPost(Post post) {
        Optional<Post> posts = postRepository.findById(post.getId());
        return posts.isPresent() ? null : postRepository.save(post).getId();
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
