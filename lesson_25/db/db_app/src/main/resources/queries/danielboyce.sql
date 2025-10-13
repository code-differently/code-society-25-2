-- database: ../../sqlite/data.db
SELECT COUNT(type) FROM media_items;


SELECT SUM(pages) FROM 
checked_out_items
 INNER JOIN guests ON  checked_out_items.email = guests.email
 INNER JOIN media_items ON media_items.id = checked_out_items.item_id
 where pages is NOT NULL



SELECT 
guests.name,checked_out_items.*
FROM 
guests LEFT JOIN checked_out_items on guests.email = checked_out_items.email;
