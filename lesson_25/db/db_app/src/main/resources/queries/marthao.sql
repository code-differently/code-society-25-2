SELECT
    type,
    COUNT(*) AS item_count
FROM
    media_items
GROUP BY
   type;

SELECT
    email,
    SUM(pages) AS total_pages
FROM
    checked_out_items
JOIN
    media_items ON checked_out_items.item_id = media_items.id
GROUP BY
    email;

//A SELECT query that shows all 5 guests and any corresponding records in the checked_out_items table.

SELECT
    guests.name,
    guests.email,
    checked_out_items.item_id,
    checked_out_items.due_date
FROM
    guests
LEFT JOIN
    checked_out_items ON guests.email = checked_out_items.email;