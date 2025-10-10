-- media items count by type
SELECT type, COUNT(*) as count
FROM media_items
GROUP BY type
ORDER BY type;

-- total pages checked out by guests
SELECT SUM(m.pages) as total_pages_checked_out
FROM checked_out_items c
INNER JOIN media_items m ON c.item_id = m.id
WHERE m.pages IS NOT NULL AND m.pages > 0;

-- guests and their records
SELECT g.type, g.name, g.email, c.item_id, c.due_date
FROM guests g
LEFT JOIN checked_out_items c ON g.email = c.email
ORDER BY g.name, c.item_id;
