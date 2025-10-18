package com.codesociety;

import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.demo.enabled", havingValue = "true", matchIfMissing = true)
@org.springframework.core.annotation.Order(1)
public class DemoRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DemoRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== Postgres Demo Application ===\n");

        System.out.println("✓ Spring Boot application started successfully!");
        System.out.println("✓ Database connection established");
        System.out.println("✓ Flyway migrations executed\n");

        // Query the users table
        List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT * FROM users ORDER BY id");

        System.out.println("Users in database:");
        System.out.println("-------------------------------------------");
        System.out.printf("%-5s %-20s %-30s%n", "ID", "Username", "Email");
        System.out.println("-------------------------------------------");

        for (Map<String, Object> user : users) {
            System.out.printf("%-5d %-20s %-30s%n",
                    user.get("id"),
                    user.get("username"),
                    user.get("email"));
        }

        System.out.println("-------------------------------------------");

        // Query the posts table
        List<Map<String, Object>> posts = jdbcTemplate.queryForList(
                "SELECT p.id, p.title, u.username, p.published "
                + "FROM posts p "
                + "JOIN users u ON p.user_id = u.id "
                + "ORDER BY p.id"
        );

        System.out.println("\nPosts in database:");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-5s %-30s %-20s %-10s%n", "ID", "Title", "Author", "Published");
        System.out.println("-------------------------------------------------------------------");

        for (Map<String, Object> post : posts) {
            System.out.printf("%-5d %-30s %-20s %-10s%n",
                    post.get("id"),
                    post.get("title"),
                    post.get("username"),
                    post.get("published"));
        }

        System.out.println("-------------------------------------------------------------------");
        System.out.println("\n✓ Demo completed successfully!\n");
    }
}
