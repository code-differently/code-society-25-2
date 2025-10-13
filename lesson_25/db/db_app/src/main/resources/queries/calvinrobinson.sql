-- Query 1: Counts of media items by type
SELECT type, COUNT(*) as count FROM media_items GROUP BY type;

-- Query 2: Sum of total pages checked out by guests
SELECT SUM(mi.pages) as total_pages_checked_out FROM checked_out_items coi JOIN media_items mi ON coi.item_id = mi.id;

-- Query 3: All guests and their corresponding checked out items
SELECT g.name, g.email, coi.item_id, coi.due_date FROM guests g LEFT JOIN checked_out_items coi ON g.email = coi.email;