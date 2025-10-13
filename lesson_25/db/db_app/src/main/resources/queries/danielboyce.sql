- database: ../../sqlite/data.db
SELECT COUNT(type) FROM media_items;


SELECT SUM(pages) FROM 
checkout_items
 INNER JOIN guests ON checkout_items.email = guest.email
 INNER JOIN media_items ON media_items.item_id = checkout_items.item_id
 where pages is NOT NULL


