SELECT type, COUNT(*) as count
FROM media_items
GROUP BY type
ORDER BY type;

SELECT SUM(mi.pages) as total_pages_checked_out
FROM checked_out_items coi
JOIN media_items mi ON coi.item_id = mi.id
WHERE mi.type = 'book' AND mi.pages IS NOT NULL;

SELECT g.name, g.email, g.type, coi.item_id, coi.due_date, mi.title, mi.type as item_type
FROM guests g
LEFT JOIN checked_out_items coi ON g.email = coi.email
LEFT JOIN media_items mi ON coi.item_id = mi.id
ORDER BY g.name;