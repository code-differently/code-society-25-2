package com.codesociety;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.codesociety.entity.Post;
import com.codesociety.entity.User;
import com.codesociety.service.PostService;
import com.codesociety.service.UserService;

@Component
@Order(2) // Run after DemoRunner
public class JpaDemo implements CommandLineRunner {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public JpaDemo(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("JPA DEMO - CRUD Operations & Queries");
        System.out.println("=".repeat(60));

        demonstrateUserOperations();
        demonstratePostOperations();
        demonstrateRelationshipQueries();
        demonstrateAdvancedQueries();

        System.out.println("✓ JPA Demo completed successfully!\n");
    }

    private void demonstrateUserOperations() {
        System.out.println("\n--- User CRUD Operations ---");

        // Create new users
        System.out.println("Creating new users...");
        try {
            User newUser1 = userService.createUser("jpa_user1", "jpa1@example.com");
            User newUser2 = userService.createUser("jpa_user2", "jpa2@example.com");

            System.out.println("✓ Created: " + newUser1.getUsername() + " (ID: " + newUser1.getId() + ")");
            System.out.println("✓ Created: " + newUser2.getUsername() + " (ID: " + newUser2.getId() + ")");

            // Find users
            System.out.println("\nFinding users...");
            Optional<User> foundUser = userService.findByUsername("jpa_user1");
            if (foundUser.isPresent()) {
                System.out.println("✓ Found user by username: " + foundUser.get());
            }

            // Update user
            System.out.println("\nUpdating user...");
            User updatedUser = userService.updateUser(newUser1.getId(), "jpa_user1_updated", "jpa1_updated@example.com");
            System.out.println("✓ Updated: " + updatedUser.getUsername() + " -> " + updatedUser.getEmail());

        } catch (Exception e) {
            System.out.println("ℹ Note: " + e.getMessage() + " (users may already exist)");
        }

        // List all users
        List<User> allUsers = userService.getAllUsers();
        System.out.println("\nTotal users in system: " + allUsers.size());
    }

    private void demonstratePostOperations() {
        System.out.println("\n--- Post CRUD Operations ---");

        // Find a user to create posts for
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            User user = users.get(0);
            System.out.println("Creating posts for user: " + user.getUsername());

            // Create posts
            Post post1 = postService.createPost(
                    "JPA Tutorial",
                    "This is a comprehensive guide to using JPA with Spring Boot.",
                    user.getId(),
                    true
            );

            Post post2 = postService.createPost(
                    "Advanced JPA Queries",
                    "Learn about custom queries, criteria API, and more.",
                    user.getId(),
                    false
            );

            System.out.println("✓ Created post: " + post1.getTitle() + " (Published: " + post1.getPublished() + ")");
            System.out.println("✓ Created post: " + post2.getTitle() + " (Published: " + post2.getPublished() + ")");

            // Update post
            Post updatedPost = postService.publishPost(post2.getId());
            System.out.println("✓ Published post: " + updatedPost.getTitle());
        }

        // Count posts
        long totalPosts = postService.countPosts();
        long publishedPosts = postService.getPublishedPosts().size();
        System.out.println("\nTotal posts: " + totalPosts + ", Published: " + publishedPosts);
    }

    private void demonstrateRelationshipQueries() {
        System.out.println("\n--- Relationship Queries ---");

        // Find users with posts
        List<User> usersWithPosts = userService.getUsersWithPosts();
        System.out.println("Users with posts: " + usersWithPosts.size());

        for (User user : usersWithPosts) {
            List<Post> userPosts = postService.getPostsByUser(user);
            System.out.println("  • " + user.getUsername() + " has " + userPosts.size() + " posts");

            // Show post titles
            for (Post post : userPosts) {
                System.out.println("    - " + post.getTitle() + " (Published: " + post.getPublished() + ")");
            }
        }
    }

    private void demonstrateAdvancedQueries() {
        System.out.println("\n--- Advanced Queries ---");

        // Search posts by title
        List<Post> tutorialPosts = postService.searchPostsByTitle("tutorial");
        System.out.println("Posts containing 'tutorial': " + tutorialPosts.size());

        // Get recent published posts with users eagerly loaded
        List<Post> recentPosts = postService.getRecentPublishedPostsWithUsers();
        System.out.println("Recent published posts: " + recentPosts.size());

        if (!recentPosts.isEmpty()) {
            System.out.println("Most recent published posts:");
            for (int i = 0; i < Math.min(3, recentPosts.size()); i++) {
                Post post = recentPosts.get(i);
                System.out.println("  • " + post.getTitle() + " by " + post.getUser().getUsername());
            }
        }

        // Count posts by user
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            User user = users.get(0);
            long userPostCount = postService.countPostsByUser(user.getId());
            System.out.println("\n" + user.getUsername() + " has " + userPostCount + " posts");
        }
    }
}
