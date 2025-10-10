-- SQL Queries for Lesson 25 Database Assignment
-- Author: GitHub Copilot
-- Date: October 10, 2025

-- Query 1: Count of media items by type
SELECT type, COUNT(*) as count
FROM media_items
GROUP BY type
ORDER BY count DESC;

-- Query 2: Sum of total pages checked out by guests
SELECT SUM(mi.pages) as total_pages_checked_out
FROM checked_out_items co
JOIN media_items mi ON co.item_id = mi.id
WHERE mi.pages IS NOT NULL AND mi.pages > 0;

-- Query 3: Show all 5 guests and any corresponding records in the checked_out_items table
SELECT g.name, g.email, g.type, co.item_id, co.due_date
FROM guests g
LEFT JOIN checked_out_items co ON g.email = co.email
ORDER BY g.name;