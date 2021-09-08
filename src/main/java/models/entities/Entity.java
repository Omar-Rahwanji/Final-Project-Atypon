package models.entities;

public abstract class Entity {
  protected Entity databaseEntity;

  public abstract void setAttributes(String[] attributes);

  public void setDatabaseEntity(Entity databaseEntity) {
    if (databaseEntity != null) this.databaseEntity = databaseEntity;
  }

  @Override
  public abstract String toString();
}
