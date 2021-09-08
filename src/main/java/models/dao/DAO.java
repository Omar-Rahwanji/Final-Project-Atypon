package models.dao;


import models.entities.Entity;

public interface DAO {
    void setAttributeValue(Entity row, String attribute, String newValue);
}
