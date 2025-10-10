CREATE TABLE library_users (
    id TEXT PRIMARY KEY,
    email TEXT UNIQUE,
    first_name TEXT,
    last_name TEXT,
    password TEXT
);