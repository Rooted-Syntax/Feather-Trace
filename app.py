from flask import Flask, render_template, request, redirect, url_for
import sqlite3
import os
from werkzeug.utils import secure_filename

app = Flask(__name__)

# Configuration
app.config['UPLOAD_FOLDER'] = 'static/uploads'
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024  # 16MB
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}

# Ensure upload folder exists
os.makedirs(app.config['UPLOAD_FOLDER'], exist_ok=True)

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

# Initialize database
def init_db():
    conn = sqlite3.connect('sightings.db')
    c = conn.cursor()
    c.execute('''
        CREATE TABLE IF NOT EXISTS sightings (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            species TEXT,
            location TEXT,
            date TEXT,
            image_path TEXT
        )
    ''')
    conn.commit()
    conn.close()

@app.route('/')
def index():
    conn = sqlite3.connect('sightings.db')
    c = conn.cursor()
    c.execute("SELECT species, location, date, image_path FROM sightings ORDER BY date DESC")
    sightings = c.fetchall()
    conn.close()
    return render_template('index.html', sightings=sightings)

@app.route('/submit_sighting', methods=['POST'])
def submit_sighting():
    species = request.form['species']
    location = request.form['location']
    date = request.form['date']
    image = request.files.get('image')

    image_path = None
    if image and allowed_file(image.filename):
        filename = secure_filename(image.filename)
        image.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        image_path = f'uploads/{filename}'

    conn = sqlite3.connect('sightings.db')
    c = conn.cursor()
    c.execute("INSERT INTO sightings (species, location, date, image_path) VALUES (?, ?, ?, ?)",
              (species, location, date, image_path))
    conn.commit()
    conn.close()

    return redirect(url_for('index'))

if __name__ == '__main__':
    init_db()
    app.run(debug=True)
