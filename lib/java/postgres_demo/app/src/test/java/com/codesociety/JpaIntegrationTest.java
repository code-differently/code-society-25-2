package com.codesociety;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.codesociety.entity.Post;
import com.codesociety.entity.User;
import com.codesociety.repository.PostRepository;
import com.codesociety.repository.UserRepository;
import com.codesociety.service.PostService;
import com.codesociety.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class JpaIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Test
    void testUserEntityCrud() {
        // Create user
        User user = new User("testuser", "test@example.com");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
        assertNotNull(savedUser.getCreatedAt());
        assertNotNull(savedUser.getUpdatedAt());

        // Find user
        Optional<User> foundUser = userRepository.findByUsername("testuser");
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getId(), foundUser.get().getId());

        // Update user
        savedUser.setEmail("updated@example.com");
        User updatedUser = userRepository.save(savedUser);
        assertEquals("updated@example.com", updatedUser.getEmail());

        // Count users
        long count = userRepository.count();
        assertTrue(count > 0);
    }

    @Test
    void testPostEntityCrud() {
        // Create user first
        User user = new User("postauthor", "author@example.com");
        User savedUser = userRepository.save(user);

        // Create post
        Post post = new Post("Test Post", "This is test content", savedUser, true);
        Post savedPost = postRepository.save(post);

        assertNotNull(savedPost.getId());
        assertEquals("Test Post", savedPost.getTitle());
        assertEquals("This is test content", savedPost.getContent());
        assertTrue(savedPost.getPublished());
        assertEquals(savedUser.getId(), savedPost.getUser().getId());

        // Find posts by user
        List<Post> userPosts = postRepository.findByUser(savedUser);
        assertFalse(userPosts.isEmpty());
        assertEquals(1, userPosts.size());

        // Find published posts
        List<Post> publishedPosts = postRepository.findByPublishedTrue();
        assertTrue(publishedPosts.stream().anyMatch(p -> p.getId().equals(savedPost.getId())));
    }

    @Test
    void testUserService() {
        // Test user creation
        User user = userService.createUser("serviceuser", "service@example.com");
        assertNotNull(user.getId());

        // Test duplicate username prevention
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("serviceuser", "different@example.com");
        });

        // Test finding user
        Optional<User> foundUser = userService.findByUsername("serviceuser");
        assertTrue(foundUser.isPresent());

        // Test user update
        User updatedUser = userService.updateUser(user.getId(), "updateduser", "updated@example.com");
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    void testPostService() {
        // Create user first
        User user = userService.createUser("blogauthor", "blog@example.com");

        // Create post
        Post post = postService.createPost("Service Post", "Content created via service", user.getId(), false);
        assertNotNull(post.getId());
        assertFalse(post.getPublished());

        // Publish post
        Post publishedPost = postService.publishPost(post.getId());
        assertTrue(publishedPost.getPublished());

        // Search posts
        List<Post> foundPosts = postService.searchPostsByTitle("service");
        assertTrue(foundPosts.stream().anyMatch(p -> p.getId().equals(post.getId())));

        // Count posts by user
        long postCount = postService.countPostsByUser(user.getId());
        assertEquals(1, postCount);
    }

    @Test
    void testRepositoryCustomQueries() {
        // Create user and posts
        User user = new User("queryuser", "query@example.com");
        User savedUser = userRepository.save(user);

        Post post1 = new Post("Query Test 1", "Content 1", savedUser, true);
        Post post2 = new Post("Query Test 2", "Content 2", savedUser, false);
        postRepository.save(post1);
        postRepository.save(post2);

        // Test custom user query
        List<User> usersWithPosts = userRepository.findUsersWithPosts();
        assertTrue(usersWithPosts.stream().anyMatch(u -> u.getId().equals(savedUser.getId())));

        // Test custom post queries
        long postCount = postRepository.countPostsByUserId(savedUser.getId());
        assertEquals(2, postCount);

        List<Post> recentPublished = postRepository.findRecentPublishedPosts();
        assertTrue(recentPublished.stream().anyMatch(p -> p.getId().equals(post1.getId())));

        // Test case insensitive search
        List<Post> searchResults = postRepository.findByTitleContainingIgnoreCase("query");
        assertEquals(2, searchResults.size());
    }
}
