package com.zhukdi.blog4you.repository;

import com.zhukdi.blog4you.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dmitry on 9/17/2018.
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTitle(String title);
}
