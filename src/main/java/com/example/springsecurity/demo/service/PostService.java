package com.example.springsecurity.demo.service;

import com.example.springsecurity.demo.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO createPost(PostDTO inputPost);

    PostDTO getPostById(Long id);
}
