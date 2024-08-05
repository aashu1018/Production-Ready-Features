package com.prod_ready_features.prod_ready_features.controllers;

import com.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @PutMapping(path = "/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost, @PathVariable Long postId){
        return postService.updatePost(inputPost, postId);
    }
}
