import sqlite3

# Connect to the database
conn = sqlite3.connect('../db/db_app/src/main/resources/sqlite/data.db')
cursor = conn.cursor()

print("=== Testing SQL Queries ===\n")

# Query 1: Count of media items by type
print("Query 1: Count of media items by type")
cursor.execute("""
SELECT type, COUNT(*) as count
FROM media_items
GROUP BY type
ORDER BY count DESC;
""")
results = cursor.fetchall()
for row in results:
    print(f"  {row[0]}: {row[1]}")
print()

# Query 2: Sum of total pages checked out by guests
print("Query 2: Sum of total pages checked out by guests")
cursor.execute("""
SELECT SUM(mi.pages) as total_pages_checked_out
FROM checked_out_items co
JOIN media_items mi ON co.item_id = mi.id
WHERE mi.pages IS NOT NULL AND mi.pages > 0;
""")
result = cursor.fetchone()
print(f"  Total pages checked out: {result[0] if result[0] else 0}")
print()

# Query 3: Show all 5 guests and any corresponding records in the checked_out_items table
print("Query 3: Show all 5 guests and any corresponding records in the checked_out_items table")
cursor.execute("""
SELECT g.name, g.email, g.type, co.item_id, co.due_date
FROM guests g
LEFT JOIN checked_out_items co ON g.email = co.email
ORDER BY g.name;
""")
results = cursor.fetchall()
for row in results:
    item_id = row[3] if row[3] else "None"
    due_date = row[4] if row[4] else "None"
    print(f"  {row[0]} ({row[1]}) [{row[2]}] - Item: {item_id}, Due: {due_date}")
print()

# Bonus: Show library users
print("Bonus: Library Users")
cursor.execute("""
SELECT id, email, first_name, last_name
FROM library_users
ORDER BY last_name, first_name;
""")
results = cursor.fetchall()
for row in results:
    print(f"  {row[2]} {row[3]} ({row[1]}) - ID: {row[0][:8]}...")

conn.close()
