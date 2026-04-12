package com.example.springsecurity.demo.service;

import com.example.springsecurity.demo.dto.PostDTO;
import com.example.springsecurity.demo.entity.PostEntity;
import com.example.springsecurity.demo.entity.User;
import com.example.springsecurity.demo.repository.PostRepository;
import exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public PostDTO createPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}", user);
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: "+id));
        return modelMapper.map(postEntity, PostDTO.class);

    }
}
