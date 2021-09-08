package models.entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer extends Entity {
    private int id;
    private String name;
    private String country;
    private int phone;
    private String email;
    private final Lock lock = new ReentrantLock();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setAttributes(String[] attributes) {
        lock.lock();
        setId(Integer.parseInt(attributes[0]));
        setName(attributes[1]);
        setCountry(attributes[2]);
        setPhone(Integer.parseInt(attributes[3]));
        setEmail(attributes[4]);
        lock.unlock();
    }

    @Override
    public String toString() {
        return getId()+","+ getName()+","+ getCountry()+","+ getPhone()+","+ getEmail();
    }
}