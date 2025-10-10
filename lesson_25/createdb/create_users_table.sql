-- Create library_users table
CREATE TABLE IF NOT EXISTS library_users (
    id TEXT PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    password TEXT NOT NULL
);

-- Insert sample users with bcrypt hashed passwords
-- Note: These passwords are bcrypt hashes of "password123" for demonstration purposes
INSERT OR REPLACE INTO library_users (id, email, firstName, lastName, password) VALUES 
('550e8400-e29b-41d4-a716-446655440001', 'john.doe@email.com', 'John', 'Doe', '$2a$10$N9qo8uLOickgx2ZMRZoMye.FqUf.tZeHTQZ.H6eSSHhFXFjjKJWg6'),
('550e8400-e29b-41d4-a716-446655440002', 'jane.smith@email.com', 'Jane', 'Smith', '$2a$10$N9qo8uLOickgx2ZMRZoMye.FqUf.tZeHTQZ.H6eSSHhFXFjjKJWg6'),
('550e8400-e29b-41d4-a716-446655440003', 'bob.wilson@email.com', 'Bob', 'Wilson', '$2a$10$N9qo8uLOickgx2ZMRZoMye.FqUf.tZeHTQZ.H6eSSHhFXFjjKJWg6'),
('550e8400-e29b-41d4-a716-446655440004', 'alice.brown@email.com', 'Alice', 'Brown', '$2a$10$N9qo8uLOickgx2ZMRZoMye.FqUf.tZeHTQZ.H6eSSHhFXFjjKJWg6'),
('550e8400-e29b-41d4-a716-446655440005', 'trishtan.husser@email.com', 'Trishtan', 'Husser', '$2a$10$N9qo8uLOickgx2ZMRZoMye.FqUf.tZeHTQZ.H6eSSHhFXFjjKJWg6');

-- Verify the data was inserted
SELECT COUNT(*) as user_count FROM library_users;
SELECT * FROM library_users ORDER BY lastName, firstName;
