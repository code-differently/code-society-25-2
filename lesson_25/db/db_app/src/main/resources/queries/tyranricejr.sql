-- SQL queries by tyranricejr for Lesson 25 assignment

-- Query 1: Count of media items by type
SELECT type, COUNT(*) as count 
FROM media_items 
GROUP BY type
ORDER BY type;

-- Query 2: Sum of total pages checked out by guests
SELECT SUM(mi.pages) as total_pages_checked_out
FROM checked_out_items coi
JOIN media_items mi ON coi.item_id = mi.id
WHERE mi.pages IS NOT NULL;

-- Query 3: All 5 guests and any corresponding records in the checked_out_items table
SELECT g.type, g.name, g.email, coi.item_id, coi.due_date
FROM guests g
LEFT JOIN checked_out_items coi ON g.email = coi.email
ORDER BY g.name, coi.due_date;
