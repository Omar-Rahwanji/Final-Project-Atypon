package models.entities;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account extends Entity {
    private int id;
    private int customerId;
    private String username;
    private String password;
    private final Lock lock = new ReentrantLock();
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setAttributes(String[] attributes) {
        try{
            lock.lock();
            setId(Integer.parseInt(attributes[0]));
            setCustomerId(Integer.parseInt(attributes[1]));
            setUsername(attributes[2]);
            setPassword(attributes[3]);
        }finally{
            lock.unlock();
        }
    }


    @Override
    public String toString() {
        return getId() + "," + getCustomerId() + "," + getUsername() + "," + getPassword();
    }
}
