package com.codesociety.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codesociety.entity.Post;
import com.codesociety.entity.User;
import com.codesociety.repository.PostRepository;
import com.codesociety.repository.UserRepository;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new post
     */
    public Post createPost(String title, String content, Long userId, Boolean published) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Post post = new Post(title, content, user, published != null ? published : false);
        return postRepository.save(post);
    }

    /**
     * Find post by ID
     */
    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Get all posts
     */
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Get all published posts
     */
    @Transactional(readOnly = true)
    public List<Post> getPublishedPosts() {
        return postRepository.findByPublishedTrue();
    }

    /**
     * Get posts by user ID
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    /**
     * Get posts by user
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    /**
     * Get published posts by user
     */
    @Transactional(readOnly = true)
    public List<Post> getPublishedPostsByUser(User user) {
        return postRepository.findByUserAndPublishedTrue(user);
    }

    /**
     * Search posts by title
     */
    @Transactional(readOnly = true)
    public List<Post> searchPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Get recent published posts
     */
    @Transactional(readOnly = true)
    public List<Post> getRecentPublishedPosts() {
        return postRepository.findRecentPublishedPosts();
    }

    /**
     * Get recent published posts with users eagerly loaded
     */
    @Transactional(readOnly = true)
    public List<Post> getRecentPublishedPostsWithUsers() {
        return postRepository.findRecentPublishedPostsWithUsers();
    }

    /**
     * Update post
     */
    public Post updatePost(Long id, String title, String content, Boolean published) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));

        if (title != null) {
            post.setTitle(title);
        }
        if (content != null) {
            post.setContent(content);
        }
        if (published != null) {
            post.setPublished(published);
        }

        return postRepository.save(post);
    }

    /**
     * Publish a post
     */
    public Post publishPost(Long id) {
        return updatePost(id, null, null, true);
    }

    /**
     * Unpublish a post
     */
    public Post unpublishPost(Long id) {
        return updatePost(id, null, null, false);
    }

    /**
     * Delete post
     */
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    /**
     * Count posts by user
     */
    @Transactional(readOnly = true)
    public long countPostsByUser(Long userId) {
        return postRepository.countPostsByUserId(userId);
    }

    /**
     * Count total posts
     */
    @Transactional(readOnly = true)
    public long countPosts() {
        return postRepository.count();
    }
}
