SELECT 
    type,
    COUNT(type)
FROM
    media_items
GROUP BY 
   type;

SELECT 
    SUM(media_items.pages)
FROM
    media_items
    JOIN checked_out_items ON media_items.id = checked_out_items.itemID;
