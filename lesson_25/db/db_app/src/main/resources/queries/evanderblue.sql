-- Query 1: Count of media items by type
SELECT type, COUNT(*)
FROM media_items
GROUP BY type;

-- Query 2: Sum of total pages checked out by guests
SELECT SUM(pages)
FROM guests;

-- Query 3: Show all guests and any corresponding records in checked_out_items table
SELECT g.*, c.item_id, c.due_date
FROM guests g
LEFT JOIN checked_out_items c ON g.email = c.email;

-- Query 4: Create library_users table
CREATE TABLE library_users (
    id TEXT PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    password TEXT NOT NULL
);

-- Query 5: Populate library_users table with sample data
INSERT INTO library_users (id, email, first_name, last_name, password) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'john.doe@example.com', 'John', 'Doe', '$2b$10$abcdefghijklmnopqrstuvwxyz123456'),
('550e8400-e29b-41d4-a716-446655440002', 'jane.smith@example.com', 'Jane', 'Smith', '$2b$10$bcdefghijklmnopqrstuvwxyz234567'),
('550e8400-e29b-41d4-a716-446655440003', 'mike.johnson@example.com', 'Mike', 'Johnson', '$2b$10$cdefghijklmnopqrstuvwxyz345678'),
('550e8400-e29b-41d4-a716-446655440004', 'sarah.wilson@example.com', 'Sarah', 'Wilson', '$2b$10$defghijklmnopqrstuvwxyz456789'),
('550e8400-e29b-41d4-a716-446655440005', 'david.brown@example.com', 'David', 'Brown', '$2b$10$efghijklmnopqrstuvwxyz567890');