SELECT type, COUNT(*)
FROM media_items
GROUP BY type;

SELECT SUM(pages)
FROM media_items
JOIN checked_out_items ON media_items.id = checked_out_items.itemId;

SELECT guests.name, guests.email, guests.type, checked_out_items.itemId, checked_out_items.dueDate
FROM guests
LEFT JOIN checked_out_items ON guests.email = checked_out_items.email;