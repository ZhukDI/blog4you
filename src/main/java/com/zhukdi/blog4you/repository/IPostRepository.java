package com.zhukdi.blog4you.repository;

import com.zhukdi.blog4you.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Dmitry on 9/23/2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface IPostRepository extends JpaRepository<Post, Long> {
}
