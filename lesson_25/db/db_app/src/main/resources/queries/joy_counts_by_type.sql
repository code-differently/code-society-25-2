SELECT type, COUNT(*) AS item_count
FROM media_items
GROUP BY type
ORDER BY type;
