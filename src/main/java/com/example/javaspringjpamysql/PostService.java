package com.example.javaspringjpamysql;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

    public User createPostOnUserId(Post newPost, long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            newPost.setUser(user);
            postRepository.save(newPost);
        }
        return user;
    }

}
