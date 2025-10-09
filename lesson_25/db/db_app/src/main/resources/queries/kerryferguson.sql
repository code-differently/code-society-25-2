SELECT media_type, COUNT(*) as count 
FROM mediaITEMS 
GROUP BY media_type;

SELECT SUM(pages)
FROM mediaITEMS, guests, JSON_EACH(guests.checkedOutItems)
WHERE JSON_EXTRACT(JSON_EACH.value, '$.itemId') = mediaITEMS.id;

SELECT guests.name, guests.email,
       JSON_EXTRACT(JSON_EACH.value, '$.itemId') as checked_out_item_id
FROM guests
LEFT JOIN JSON_EACH(guests.checkedOutItems);