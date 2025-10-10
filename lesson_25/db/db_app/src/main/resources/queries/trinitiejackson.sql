SELECT type, COUNT(*) AS count
FROM media_items
GROUP BY type;

SELECT SUM(pages) 
FROM media_items
JOIN checked_out_items ON media_items.id = checked_out_items.item_id;

SELECT guests.name, guests.email, media_items.title
FROM guests
LEFT JOIN checked_out_items ON guests.email = checked_out_items.email
LEFT JOIN media_items ON checked_out_items.item_id = media_items.id;

CREATE TABLE library_users (
    id UUID PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    password_hash TEXT NOT NULL
);