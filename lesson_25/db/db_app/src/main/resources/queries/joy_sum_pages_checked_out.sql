SELECT SUM(mi.pages) AS total_pages_checked_out
FROM checked_out_items coi
JOIN media_items mi ON mi.id = coi.item_id;
