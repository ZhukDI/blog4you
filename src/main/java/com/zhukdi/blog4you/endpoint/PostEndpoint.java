package com.zhukdi.blog4you.endpoint;

import com.zhukdi.blog4you.entity.Post;
import com.zhukdi.blog4you.gs_ws.AddPostRequest;
import com.zhukdi.blog4you.gs_ws.AddPostResponse;
import com.zhukdi.blog4you.gs_ws.DeletePostResponse;
import com.zhukdi.blog4you.gs_ws.DeletePostRequest;
import com.zhukdi.blog4you.gs_ws.GetAllPostsResponse;
import com.zhukdi.blog4you.gs_ws.GetPostByIdRequest;
import com.zhukdi.blog4you.gs_ws.GetPostByIdResponse;
import com.zhukdi.blog4you.gs_ws.PostInfo;
import com.zhukdi.blog4you.gs_ws.ServiceStatus;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPostRequest")
    @ResponsePayload
    public AddPostResponse addPost(@RequestPayload AddPostRequest request) {
        AddPostResponse response = new AddPostResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Post post = new Post();
        post.setBlogId(request.getBlogId());
        post.setUserId(request.getUserId());
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setStatus(request.getStatus());
//        post.setDateCreated(request.getDateCreated().toGregorianCalendar().getTime());
//        post.setDateUpdated(request.getDateUpdated().toGregorianCalendar().getTime());
        Long flag = postService.addPost(post);
        if (flag == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            PostInfo postInfo = new PostInfo();
            BeanUtils.copyProperties(post, postInfo);
            response.setPostInfo(postInfo);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePostRequest")
    @ResponsePayload
    public DeletePostResponse deletePost(@RequestPayload DeletePostRequest request) {
        Post post = postService.getPostById(request.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (post == null ) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            postService.deletePost(post);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeletePostResponse response = new DeletePostResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
