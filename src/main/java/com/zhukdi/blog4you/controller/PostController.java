package com.zhukdi.blog4you.controller;

import com.zhukdi.blog4you.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitry on 9/23/2018.
 */
@RestController
@RequestMapping("posts")
public class PostController {
    private int counter = 4;
    private List<Map<String, String>> posts = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("body", "Lorem body");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("body", "Lorem body 2");}});
        add(new HashMap<String, String>() {{ put("id", "3"); put("body", "Lorem body 3");}});
    }};

    @GetMapping
    public List getPosts() {
        return posts;
    }

    @GetMapping("{id}")
    public Map<String, String> getPostById(@PathVariable String id) {
        return getPost(id);
    }

    private Map<String, String> getPost(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> addPost(@RequestBody Map<String, String> post){
        post.put("id", String.valueOf(counter++));
        posts.add(post);
        return post;
    }

    @PutMapping("{id}")
    public Map<String, String> updatePost(@PathVariable String id, @RequestBody Map<String, String> post) {
        Map<String, String> postFromDb = getPost(post.get("id"));
        postFromDb.putAll(post);
        postFromDb.put("id", id);
        return postFromDb;
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable String id) {
        Map<String, String> post = getPost(id);
        posts.remove(post);
    }
}
