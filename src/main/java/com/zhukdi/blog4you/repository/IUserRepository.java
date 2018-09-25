package com.zhukdi.blog4you.repository;

import com.zhukdi.blog4you.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry on 9/26/2018.
 */
public interface IUserRepository extends JpaRepository<User, Long> {
}
