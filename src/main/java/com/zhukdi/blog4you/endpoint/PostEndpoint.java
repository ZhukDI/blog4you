package com.zhukdi.blog4you.endpoint;

import com.zhukdi.blog4you.entity.Post;
import com.zhukdi.blog4you.gs_ws.GetAllPostsResponse;
import com.zhukdi.blog4you.gs_ws.GetPostByIdRequest;
import com.zhukdi.blog4you.gs_ws.GetPostByIdResponse;
import com.zhukdi.blog4you.gs_ws.PostInfo;
import com.zhukdi.blog4you.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 9/17/2018.
 */
@Endpoint
public class PostEndpoint {
    private static final String NAMESPACE_URI = "http://zhukdi.com/blog4you/post-ws";

    @Autowired
    private IPostService postService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPostByIdRequest")
    @ResponsePayload
    public GetPostByIdResponse getPost(@RequestPayload GetPostByIdRequest request) {
        GetPostByIdResponse response = new GetPostByIdResponse();
        PostInfo postInfo = new PostInfo();
        BeanUtils.copyProperties(postService.getPostById(request.getId()), postInfo);
        response.setPostInfo(postInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPostsRequest")
    @ResponsePayload
    public GetAllPostsResponse getAllPosts() {
        GetAllPostsResponse response = new GetAllPostsResponse();
        List<PostInfo> postInfoList = new ArrayList<>();
        List<Post> postList = postService.getAllPosts();
        for (int i = 0; i < postList.size(); i++) {
            PostInfo ob = new PostInfo();
            BeanUtils.copyProperties(postList.get(i), ob);
            postInfoList.add(ob);
        }
        response.getPostInfo().addAll(postInfoList);
        return response;
    }
}
