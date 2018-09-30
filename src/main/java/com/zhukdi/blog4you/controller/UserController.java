package com.zhukdi.blog4you.controller;

import com.zhukdi.blog4you.entity.User;
import com.zhukdi.blog4you.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dmitry on 9/26/2018.
 */
@RestController
@RequestMapping("users")
public class UserController {
    private final IUserRepository userRepository;

    @Autowired
    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> get() {
        return userRepository.findAll();
    }

    @PostMapping
    public User add(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User postFromDb, @RequestBody User post) {
        BeanUtils.copyProperties(post, postFromDb, "id");
        return userRepository.save(postFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepository.delete(user);
    }
}
