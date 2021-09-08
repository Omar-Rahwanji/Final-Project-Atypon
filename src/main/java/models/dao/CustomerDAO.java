package models.dao;


import models.entities.Entity;
import models.entities.Customer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerDAO implements DAO{
    private final Lock lock = new ReentrantLock();

    @Override
    public void setAttributeValue(Entity row, String attribute, String newValue) {
        lock.lock();
        Customer customer = (Customer) row;
        switch(attribute){
            case "id":
                customer.setId(Integer.parseInt(newValue));
                break;
            case "name":
                customer.setName(newValue);
                break;
            case "country":
                customer.setCountry(newValue);
                break;
            case "phone":
                customer.setPhone(Integer.parseInt(newValue));
                break;
            case "email":
                customer.setEmail(newValue);
                break;
            default:
                break;
        }
        lock.unlock();
    }

    public String getValueByName(Customer customer, String name) {
         switch (name) {
             case "id":
                 return Integer.toString(customer.getId());
             case "name":
                 return customer.getName();
             case "country":
                 return customer.getCountry();
             case "phone":
                 return Integer.toString(customer.getPhone());
             case "email":
                 return customer.getEmail();
             default:
                 return "";
        }
    }
}
