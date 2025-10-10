SELECT
type, 
COUNT(id) AS item_count
FROM
media_items
GROUP BY
type;


SELECT
SUM(mi.page_count) AS total_pages_checked_out
FROM
checked_out_items coi
JOIN
media_items mi ON coi.item_id = mi.id;


SELECT
g.id AS guest_id,
g.first_name,
g.last_name,
coi.item_id,
coi.checkout_date
FROM 
guests g
LEFT OUTER JOIN
checked_out_items coi ON g.id = coi.guest_id
ORDER BY
g.id, coi.checkout_date;

