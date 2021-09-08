package models.dao;


import models.entities.Entity;

public class NullDAO implements DAO{
    @Override
    public void setAttributeValue(Entity row, String attribute, String newValue) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }
}
