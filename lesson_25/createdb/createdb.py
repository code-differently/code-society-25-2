import os
import pandas as pd
import numpy as np
import sqlite3

# Step 1: Load the CSV file into a pandas DataFrame
media_items_df = pd.read_csv('../db/db_app/src/main/resources/csv/media_items.csv')
guests_df = pd.read_csv('../db/db_app/src/main/resources/csv/guests.csv')
checked_out_items_df = pd.read_csv('../db/db_app/src/main/resources/csv/checked_out_items.csv')
checked_out_items_df['due_date'] = pd.to_datetime(checked_out_items_df['due_date']).values.astype(np.int64)

# Step 2: Create a connection to the SQLite database
# Note: This will create the database file if it doesn't exist already
os.makedirs('../db/db_app/src/main/resources/sqlite/', exist_ok=True)
conn = sqlite3.connect('../db/db_app/src/main/resources/sqlite/data.db')

# Step 3: Write the DataFrame to the SQLite database
media_items_df.to_sql('media_items', conn, if_exists='replace', index=False)
guests_df.to_sql('guests', conn, if_exists='replace', index=False)
checked_out_items_df.to_sql('checked_out_items', conn, if_exists='replace', index=False)

# Step 4: Execute the SQL script to create users table and add sample data
with open('create_users_table.sql', 'r') as sql_file:
    sql_script = sql_file.read()
    # Execute each statement separately
    for statement in sql_script.split(';'):
        if statement.strip():
            conn.execute(statement)
    conn.commit()

print("Database created successfully with all tables including library_users!")

# Don't forget to close the connection
conn.close()