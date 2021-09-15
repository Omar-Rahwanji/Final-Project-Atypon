package models.operations_components;

import cache_components.Cache;
import cache_components.CacheItem;
import cache_components.CacheRecord;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import models.entities.Entity;
import models.entities.EntityFactory;
import models.entities.NullEntity;

import java.io.FileReader;

public class OperationsUtil {
    private static final String TABLE1 = "customersTable.csv";
    private static final String TABLE2 = "accountsTable.csv";

    private static final String ENTITY1 = "customer";
    private static final String ENTITY2 = "account";
    private static final String TABLES_PATH = "C:\\Users\\omarr\\Desktop\\ServletWebApp\\target\\ServletWebApp-1.0\\WEB-INF\\classes\\tables\\";
    public static String table;
    public static String entity;

    private static final EntityFactory entityFactory = EntityFactory.getEntityFactory();

    private OperationsUtil(){}

    public static void chooseTable(int tableIndex){
        if(tableIndex == 0){
            table = TABLE1;
            entity = ENTITY1;
        }
        else{
            table = TABLE2;
            entity = ENTITY2;
        }
    }

    public static int getTableIndex(String table){
        if(table.equalsIgnoreCase(ENTITY1))
            return 0;
        else
            return 1;
    }

    public static String[] getDelimiterTokens(String delimiter){ // [col,val,comparison]
        String[] tokens = new String[3];
        String[] delimiterTokens = delimiter.split("=");
        tokens[0]=delimiterTokens[0];
        tokens[1]=delimiterTokens[1];
        tokens[2]="=";
        return tokens;
    }

    public static Entity searchRecords(int tableIndex, String delimiter, Cache[] cachedRecords) {
        chooseTable(tableIndex);
        try (FileReader fileReader = new FileReader(TABLES_PATH + table);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()) {
            String[] nextRecord;
            String[] tokens = getDelimiterTokens(delimiter);
            String comparisonToken=tokens[2];
            int column = 0;
            if (comparisonToken.equals("=")) {
                Entity selectedRecord = cachedRecords[tableIndex].get(Integer.parseInt(tokens[1]));
                if (selectedRecord.equals(NullEntity.getInstance())){ //Cache miss
                    System.out.println("cache miss & id = "+tokens[1]);
                    while ((nextRecord = csvReader.readNext()) != null){
                        if (nextRecord[column].equals(tokens[1])) { //Record Id
                            Entity row = entityFactory.getEntityByType(entity);
                            row.setAttributes(nextRecord);
                            CacheItem cachedRecord = new CacheRecord(row);
                            cachedRecords[tableIndex].put(Integer.parseInt(tokens[1]), cachedRecord);
                            return row;
                        }
                    }
                }
                else{
                    System.out.println("cache hit & id = "+tokens[1]);
                    return selectedRecord;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NullEntity.getInstance();
    }
}
