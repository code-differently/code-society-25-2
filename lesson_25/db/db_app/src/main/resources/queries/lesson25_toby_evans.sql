SELECT type, COUNT(*) AS total_count FROM media_items GROUP BY type;

SELECT SUM(pages) AS total_pages_checked_out FROM checked_out_items;

SELECT g.id AS guest_id, g.first_name, g.last_name, c.item_id, c.checked_out_date, c.return_date
FROM guests g
LEFT JOIN checked_out_items c ON g.id = c.guest_id;
