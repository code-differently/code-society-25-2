-- Lesson 25 SQL Queries by Trishtan Husser
-- Query exercises for database operations

-- Query 1: Count of media items by type
-- This query returns the counts of media items grouped by their type
SELECT type, COUNT(*) as count
FROM media_items
GROUP BY type
ORDER BY count DESC;

-- Query 2: Sum of total pages checked out by guests
-- This query calculates the total pages from all checked out items
SELECT SUM(mi.pages) as total_pages_checked_out
FROM checked_out_items coi
JOIN media_items mi ON coi.itemId = mi.id
WHERE mi.pages IS NOT NULL;

-- Query 3: All guests with their corresponding checked out items (LEFT JOIN)
-- This query shows all 5 guests and any corresponding records in the checked_out_items table
SELECT g.name, 
       g.email, 
       g.type,
       coi.itemId,
       coi.dueDate,
       mi.title,
       mi.type as media_type
FROM guests g
LEFT JOIN checked_out_items coi ON g.email = coi.email
LEFT JOIN media_items mi ON coi.itemId = mi.id
ORDER BY g.name, coi.dueDate;
