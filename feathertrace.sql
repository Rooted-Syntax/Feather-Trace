# feathertrace.py

import sqlite3
from datetime import datetime

DB_NAME = "feathertrace.db"

def init_db():
    """Initialize the database with tables for sightings and species."""
    conn = sqlite3.connect(DB_NAME)
    cursor = conn.cursor()
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS species (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            common_name TEXT NOT NULL,
            scientific_name TEXT,
            habitat TEXT
        )
    ''')
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS sightings (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            species_id INTEGER,
            location TEXT,
            timestamp TEXT,
            notes TEXT,
            FOREIGN KEY (species_id) REFERENCES species(id)
        )
    ''')
    
    conn.commit()
    conn.close()

