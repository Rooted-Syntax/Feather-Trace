# 🐦 Feather-Trade: Bird watching Meets Tech

This project is a nature-inspired data logging tool for tracking bird sightings in a garden, at your home, sanctuar or anywhere you please. 
Built with Python and SQLite, it allows users to record species, timestamps, locations, and notes, and visualize seasonal patterns using dashboards.

## 🌿 Features
- Log bird sightings with species, date/time, location, and notes
- Store data in a relational SQLite database
- Query sightings by season, species, or location
- Optional dashboard integration with Power BI or Tableau
- Flask-based web interface for easy data entry (optional)

## 🛠️ Tech Stack
- Python 3
- SQLite
- SQLAlchemy
- Flask 
- Tableau (for visualization)
  
## 🐦 Sample Use Case
Ashley logs a sighting of a Northern Cardinal in her West Virginia garden on Sept 5, 2025. 
The logger stores the entry and later visualizes seasonal bird activity using Tableau.

## 🚀 Getting Started
1. Clone the repo  
2. Run `logger.py` to log sightings  
3. Use `queries.py` to explore patterns  
4. Connect `bird_data.db` to Power BI or Tableau for dashboards

## 📊 Future Enhancements
- Add image upload for sightings
- GPS-based location tagging
- Weather API integration
- Moon phase integration and logging

