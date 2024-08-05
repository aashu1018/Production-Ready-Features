package com.prod_ready_features.prod_ready_features.services;

import com.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found with id :" + postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with id :" + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);
        PostEntity savedPost = postRepository.save(olderPost);
        return modelMapper.map(savedPost, PostDTO.class);
    }
}
