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
    id TEXT PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

INSERT INTO library_users (id, email, first_name, last_name, password_hash)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'trinitie.jackson@example.com', 'Trinitie', 'Jackson', '$2y$10$fBjbX04w/CPZ4YZNJpLBR.hyuz1YXbyIFj92udVrckYEXjPzhoDHq'),
       ('550e8400-e29b-41d4-a716-446655440001', 'john.doe@example.com', 'John', 'Doe', '$2y$10$QnMskk1wZ.T5148M9aALAuAeCHPRW72e3aN41Ysms4gOu1/YSjJn.');
