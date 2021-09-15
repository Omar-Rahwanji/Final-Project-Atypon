package models.operations_components;

import cache_components.Cache;
import cache_components.CacheRecord;
import models.entities.Entity;
import models.entities.EntityFactory;
import models.dao.DAO;
import models.dao.DAOFactory;
import models.entities.NullEntity;

import java.io.*;

public class Operations implements Read, Insert, Update, Delete {
    private final EntityFactory entityFactory = EntityFactory.getEntityFactory();
    private static final String TABLES_PATH = "C:\\Users\\omarr\\Desktop\\ServletWebApp\\target\\ServletWebApp-1.0\\WEB-INF\\classes\\tables\\";

    @Override
    public Entity readRecord(int tableIndex, String delimiter, Cache[] databaseCache) {
        return OperationsUtil.searchRecords(tableIndex, delimiter, databaseCache);
    }

    @Override
    public boolean insertRecord(int tableIndex, String newRecord, Cache[] databaseCache) {
        OperationsUtil.chooseTable(tableIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TABLES_PATH + OperationsUtil.table, true))) {
            String[] recordAttributes = newRecord.split(",");
            int recordId = Integer.parseInt(recordAttributes[0]);
            Entity similarRecord = OperationsUtil.searchRecords(tableIndex,"id=" + recordId, databaseCache);
            if(tableIndex == 1){
                int ForeignKeyId = Integer.parseInt(recordAttributes[1]);
                Entity similarRecordForeignKey = OperationsUtil.searchRecords(0,"id=" + ForeignKeyId, databaseCache);
                if(similarRecordForeignKey.equals(NullEntity.getInstance()))
                    return false;
                OperationsUtil.chooseTable(tableIndex);
            }
            if (similarRecord.equals(NullEntity.getInstance())) {
                Entity row = entityFactory.getEntityByType(OperationsUtil.entity);
                row.setAttributes(recordAttributes);
                row.setDatabaseEntity(row);
                writer.write(row + System.lineSeparator());
                writer.flush();
                databaseCache[tableIndex].put(recordId, new CacheRecord(row));
                return true;
            }
            return false;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRecord(int tableIndex, String updateStatement, Cache[] databaseCache) {
        OperationsUtil.chooseTable(tableIndex);
        DAO entitiesDAO = DAOFactory.getInstanceByType(OperationsUtil.entity);
        try {
            String[] commands = updateStatement.split(","); // column=value,column=new_value
            Entity toBeUpdatedRecord = OperationsUtil.searchRecords(tableIndex, commands[0], databaseCache);
            String[] recordDelimiter = OperationsUtil.getDelimiterTokens(commands[0]);
            String[] newColumnValue = OperationsUtil.getDelimiterTokens(commands[1]);
            int recordId = Integer.parseInt(recordDelimiter[1]);

            if (newColumnValue[0].equals("id")) // ID should remain unique
                return false;
            if(tableIndex == 1 && newColumnValue[0].equals("customerId")){
                int ForeignKeyId = Integer.parseInt(newColumnValue[1]);
                Entity similarRecordForeignKey = OperationsUtil.searchRecords(0,"id=" + ForeignKeyId, databaseCache);
                if(similarRecordForeignKey.equals(NullEntity.getInstance()))
                    return false;
                OperationsUtil.chooseTable(tableIndex);
            }

            if (newColumnValue[0].equals("id")) // ID should remain unique
            databaseCache[tableIndex].deleteByKey(recordId);
            deleteRecord(tableIndex, commands[0], databaseCache);
            OperationsUtil.chooseTable(tableIndex);
            entitiesDAO.setAttributeValue(toBeUpdatedRecord, newColumnValue[0], newColumnValue[1]);
            insertRecord(tableIndex, toBeUpdatedRecord.toString(), databaseCache);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRecord(int tableIndex, String delimiter, Cache[] databaseCache) {
        OperationsUtil.chooseTable(tableIndex);
        try (BufferedReader reader = new BufferedReader(new FileReader(TABLES_PATH + OperationsUtil.table));
             BufferedWriter writer = new BufferedWriter(new FileWriter(TABLES_PATH + "temp-"+ OperationsUtil.table))) {
            Entity recordToDelete = OperationsUtil.searchRecords(tableIndex, delimiter, databaseCache);
            String recordFromDatabase;
            writer.write("");
            while (((recordFromDatabase = reader.readLine()) != null)) {
                if (recordFromDatabase.equals(recordToDelete.toString()))
                    continue;
                writer.append(recordFromDatabase).append(System.lineSeparator());
                writer.flush();
            }
            String[] tokens= OperationsUtil.getDelimiterTokens(delimiter);
            databaseCache[tableIndex].deleteByKey(Integer.parseInt(tokens[1]));
            try (BufferedReader input = new BufferedReader(new FileReader( TABLES_PATH + "temp-"+ OperationsUtil.table));
                 BufferedWriter output = new BufferedWriter(new FileWriter(TABLES_PATH + OperationsUtil.table))) {
                output.write("");
                while ((recordFromDatabase = input.readLine()) != null) {
                    output.append(recordFromDatabase).append(System.lineSeparator());
                    output.flush();
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}