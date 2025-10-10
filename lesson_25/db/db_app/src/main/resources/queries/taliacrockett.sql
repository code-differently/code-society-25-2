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
    JOIN 
