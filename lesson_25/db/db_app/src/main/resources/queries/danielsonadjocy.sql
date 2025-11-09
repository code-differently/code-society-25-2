select type , COUNT(*) as count 
from mediaItems group by type;

select SUM(pages) as total_pages 
from checked_out_items INNER JOIN mediaItems 
ON checked_out_items.item_id = mediaItems.id 
where mediaItems.pages IS NOT NULL;


select guests.id, guests.name, checked_out_items.item_id, checked_out_items.checked_out_date
from guests
left join checked_out_items on guests.id = checked_out_items.guest_id;

CREATE Table library_users (
    id UUID PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    password_hash CHAR(60) NOT NULL
);

-- Inserting users: passwords are all "password123" hashed with bcrypt.
INSERT INTO library_users (id, email, first_name, last_name, password_hash) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'john.doe@example.com', 'John', 'Doe', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.'),
('550e8400-e29b-41d4-a716-446655440002', 'jane.smith@example.com', 'Jane', 'Smith', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.'),
('550e8400-e29b-41d4-a716-446655440003', 'bob.johnson@example.com', 'Bob', 'Johnson', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.'),
('550e8400-e29b-41d4-a716-446655440004', 'alice.williams@example.com', 'Alice', 'Williams', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.');