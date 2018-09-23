package com.zhukdi.blog4you.repository;

import com.zhukdi.blog4you.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry on 9/23/2018.
 */
public interface IPostRepository extends JpaRepository<Post, Long> {
}
