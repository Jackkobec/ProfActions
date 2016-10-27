import roles.Car;
import roles.TestClass;
import roles.User;

import java.lang.reflect.Field;

/**
 * Created by Jack on 25.10.2016.
 */
public class MiniOutputTests {
    public static void main(String[] args) {

        User us = new User("Vasa", 22, new int[]{1, 2, 3}, new Car("Toyota", new TestClass("Test")));

        Class cl = us.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.toString();
            String type = field.getType().toString();
            int start = type.lastIndexOf(".");
            System.out.println(fieldName + " FIELD TYPE: " + type.substring(start + 1));
        }

    }
}
