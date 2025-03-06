package com.example.javaspringjpamysql;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else throw new EntityNotFoundException();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @Valid Post newPost) {
        return ResponseEntity.ok(postService.createPost(newPost));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<User> createPostOnUserId(@RequestBody Post newPost, @PathVariable long userId) {
        User user = postService.createPostOnUserId(newPost, userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else throw new EntityNotFoundException();
    }


}
