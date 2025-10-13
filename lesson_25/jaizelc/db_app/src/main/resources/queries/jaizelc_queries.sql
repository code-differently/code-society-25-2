-- Query 1: Count of media items by type
SELECT type, COUNT(*) as count 
FROM media_items 
GROUP BY type;

-- Query 2: Sum of total pages checked out by guests
SELECT SUM(m.pages) as total_pages_checked_out
FROM checked_out_items c
JOIN media_items m ON c.item_id = m.id
WHERE m.pages IS NOT NULL;

-- Query 3: All guests with their corresponding checked out items (LEFT JOIN to show all guests)
SELECT g.name, g.email, g.type as guest_type, 
       c.item_id, m.title, m.type as media_type, c.due_date
FROM guests g
LEFT JOIN checked_out_items c ON g.email = c.email
LEFT JOIN media_items m ON c.item_id = m.id
ORDER BY g.name;
