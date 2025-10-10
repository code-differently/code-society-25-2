import sqlite3
import bcrypt
import uuid

# Create a connection to the SQLite database
conn = sqlite3.connect('../db/db_app/src/main/resources/sqlite/data.db')
cursor = conn.cursor()

# Create the library_users table
cursor.execute('''
CREATE TABLE IF NOT EXISTS library_users (
    id TEXT PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    password TEXT NOT NULL
)
''')

# Sample users data
users = [
    {
        'id': str(uuid.uuid4()),
        'email': 'john.doe@example.com',
        'first_name': 'John',
        'last_name': 'Doe',
        'password': 'password123'
    },
    {
        'id': str(uuid.uuid4()),
        'email': 'jane.smith@example.com',
        'first_name': 'Jane',
        'last_name': 'Smith',
        'password': 'securepass456'
    },
    {
        'id': str(uuid.uuid4()),
        'email': 'mike.johnson@example.com',
        'first_name': 'Mike',
        'last_name': 'Johnson',
        'password': 'mypassword789'
    },
    {
        'id': str(uuid.uuid4()),
        'email': 'sarah.wilson@example.com',
        'first_name': 'Sarah',
        'last_name': 'Wilson',
        'password': 'libraryuser2024'
    },
    {
        'id': str(uuid.uuid4()),
        'email': 'admin@library.org',
        'first_name': 'Library',
        'last_name': 'Admin',
        'password': 'adminpass2024'
    }
]

# Insert users with bcrypt hashed passwords
for user in users:
    # Hash the password using bcrypt
    password_bytes = user['password'].encode('utf-8')
    salt = bcrypt.gensalt()
    hashed_password = bcrypt.hashpw(password_bytes, salt)

    cursor.execute('''
    INSERT OR REPLACE INTO library_users (id, email, first_name, last_name, password)
    VALUES (?, ?, ?, ?, ?)
    ''', (user['id'], user['email'], user['first_name'], user['last_name'], hashed_password.decode('utf-8')))

# Commit the changes and close the connection
conn.commit()
conn.close()

print("Library users table created and populated with 5 users!")
print("Users created:")
for user in users:
    print(f"- {user['first_name']} {user['last_name']} ({user['email']})")
