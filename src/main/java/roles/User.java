package roles;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Jack on 23.10.2016.
 */
public class User {
    public String name;
    public int age;
    public int[] mas;
    public Car car;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, int[] mas) {
        this.name = name;
        this.age = age;
        this.mas = mas;
    }

    public User(String name, int age, int[] mas, Car car) {
        this.name = name;
        this.age = age;
        this.mas = mas;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mas=" + Arrays.toString(mas) +
                ", car=" + car +
                '}';
    }
}
