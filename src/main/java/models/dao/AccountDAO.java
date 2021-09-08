package models.dao;


import cache_components.Cache;
import cache_components.DatabaseCache;
import models.entities.Entity;
import models.entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDAO implements DAO{
    private final Lock lock = new ReentrantLock();

    @Override
    public void setAttributeValue(Entity row, String attribute, String newValue) {
        lock.lock();
        Account account = (Account) row;
        switch(attribute){
            case "id":
                account.setId(Integer.parseInt(newValue));
                break;
            case "customerId":
                account.setCustomerId(Integer.parseInt(newValue));
                break;
            case "username":
                account.setUsername(newValue);
                break;
            case "password":
                account.setPassword(newValue);
                break;
            default:
                break;
        }
        lock.unlock();
    }

    public String getValueByName(Entity record, String name) {
        Account account = (Account) record;
        switch (name) {
            case "id":
                return Integer.toString(account.getId());
            case "customerId":
                return Integer.toString(account.getCustomerId());
            case "username":
                return account.getUsername();
            case "password":
                return account.getPassword();
            default:
                return "";
        }
    }

    public List<Entity> getAccounts(String delimiter, Cache[] cachedRecords){
        DatabaseCache accounts = (DatabaseCache) cachedRecords[1];
        int customerId = Integer.parseInt(delimiter.split("=")[1]);
        List<Entity> customerAccounts = new ArrayList<>();
        for (int accountId : accounts.getAllKeys()) {
            Account account = (Account) accounts.get(accountId);
            if(account.getCustomerId() == customerId)
                customerAccounts.add(account);
        }
        return  customerAccounts;
    }
}
