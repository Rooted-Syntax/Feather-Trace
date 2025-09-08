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
	
