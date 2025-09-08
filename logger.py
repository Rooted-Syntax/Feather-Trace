- FeatherTrace 


Modules.py
from sqlalchemy import Column, Integer, String, DateTime, ForeignKey
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class BirdSpecies(Base):
    __tablename__ = 'bird_species'
    id = Column(Integer, primary_key=True)
    common_name = Column(String)
    scientific_name = Column(String)
    notes = Column(String)

class Sightings(Base):
    __tablename__ = 'sightings'
    id = Column(Integer, primary_key=True)
    species_id = Column(Integer, ForeignKey('bird_species.id'))
    date_time = Column(DateTime)
    location = Column(String)
    notes = Column(String)
from datetime import datetime
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from models import Base, BirdSpecies, Sightings

engine = create_engine('sqlite:///bird_data.db')
Base.metadata.create_all(engine)
Session = sessionmaker(bind=engine)
session = Session()

def log_sighting():
    species = input("Bird species (common name): ")
    location = input("Location: ")
    notes = input("Notes: ")

    existing = session.query(BirdSpecies).filter_by(common_name=species).first()
    if not existing:
        new_species = BirdSpecies(common_name=species)
        session.add(new_species)
        session.commit()
        species_id = new_species.id
    else:
        species_id = existing.id

    sighting = Sightings(
        species_id=species_id,
        date_time=datetime.now(),
        location=location,
        notes=notes
    )
    session.add(sighting)
    session.commit()
    print("Sighting logged!")

if __name__ == "__main__":
    log_sighting()
	
	
	
