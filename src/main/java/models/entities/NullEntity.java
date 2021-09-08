package models.entities;


public class NullEntity extends Entity {
    public static final NullEntity nullEntity = new NullEntity();
    private NullEntity(){}

    public static NullEntity getInstance(){
        return nullEntity;
    }
    @Override
    public void setAttributes(String[] attributes) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDatabaseEntity(Entity databaseEntity) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }
}
