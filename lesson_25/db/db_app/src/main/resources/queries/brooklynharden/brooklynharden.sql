-- database: ../../sqlite/data.db
-- database: ../../sqlite/data.db
SELECT
COUNT(type)
FROM
media_items;


SELECT guests.name, SUM(media_items.pages)
FROM guests
JOIN checked_out_items ON guests.email = checked_out_items.email
INNER JOIN media_items ON checked_out_items.item_id = media_items.id
GROUP BY 
guests.name;


SELECT guests.name, checked_out_items.*
FROM guests
LEFT JOIN checked_out_items ON guests.email = checked_out_items.email
GROUP BY guests.name;


