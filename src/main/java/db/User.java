package db;

import java.util.Date;

/**
 * Created by serhii on 17.01.16.
 */
public class User {

    int id;
    String name;
    double amount;
    Date date;
    String phone;

    public User(int id, String name, double amount, Date date, String phone) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.phone = phone;
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                '}';
    }
}
