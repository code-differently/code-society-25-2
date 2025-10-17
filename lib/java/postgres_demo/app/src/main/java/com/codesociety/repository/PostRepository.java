package com.codesociety.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codesociety.entity.Post;
import com.codesociety.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Find all published posts
     */
    List<Post> findByPublishedTrue();

    /**
     * Find all posts by user
     */
    List<Post> findByUser(User user);

    /**
     * Find all posts by user ID
     */
    List<Post> findByUserId(Long userId);

    /**
     * Find published posts by user
     */
    List<Post> findByUserAndPublishedTrue(User user);

    /**
     * Find posts containing title (case insensitive)
     */
    List<Post> findByTitleContainingIgnoreCase(String title);

    /**
     * Custom query to count posts by user
     */
    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    long countPostsByUserId(@Param("userId") Long userId);

    /**
     * Custom query to find recent published posts
     */
    @Query("SELECT p FROM Post p WHERE p.published = true ORDER BY p.createdAt DESC")
    List<Post> findRecentPublishedPosts();

    /**
     * Custom query to find recent published posts with users eagerly loaded
     */
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.published = true ORDER BY p.createdAt DESC")
    List<Post> findRecentPublishedPostsWithUsers();
}
