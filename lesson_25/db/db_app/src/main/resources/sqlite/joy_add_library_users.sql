CREATE TABLE IF NOT EXISTS library_users (
  id TEXT PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  password_hash TEXT NOT NULL
);

INSERT OR IGNORE INTO library_users (id, email, first_name, last_name, password_hash) VALUES
('11111111-1111-1111-1111-111111111111','joy@example.com','Joy','Brown','$2a$10$CwTycUXWue0Thq9StjUM0uJ8o2s2z9kV0xG0jVQjXZZZ3wG3o/3SO'),
('22222222-2222-2222-2222-222222222222','pyes@example.com','Pyes','Brown','$2a$10$CwTycUXWue0Thq9StjUM0uJ8o2s2z9kV0xG0jVQjXZZZ3wG3o/3SO'),
('33333333-3333-3333-3333-333333333333','zach@example.com','Zach','Brown','$2a$10$CwTycUXWue0Thq9StjUM0uJ8o2s2z9kV0xG0jVQjXZZZ3wG3o/3SO');
