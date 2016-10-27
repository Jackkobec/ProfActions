import com.google.gson.Gson;
import gson_actions.IJSONconvertor;
import gson_actions.IncorrectClassException;
import gson_actions.JSONconvertorImplement;
import roles.Car;
import roles.FirstFieldAsClass;
import roles.TestClass;
import roles.User;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by Jack on 23.10.2016.
 */
public class TestConvertor {
    public static void main(String[] args) {
        IJSONconvertor ijsoNconvertor = new JSONconvertorImplement();
        Car car = new Car("Toyota");
        User us = new User("Vasa", 22, new int[]{1, 2, 3}, new Car("Toyota", new TestClass(22, "Test")));
        Class cl = us.getClass();
        Field[] fields = cl.getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        Gson gson = new Gson();

        int[] mas = {1, 2, 3};
        System.out.println();
        System.out.println("JSON: ");
        //System.out.println(gson.toJson(us));
        System.out.println(ijsoNconvertor.objectToJson(us));
        System.out.println("From JSON: ");
        User user = gson.fromJson("{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Toyota\",\"cl\":{\"test\":\"Test\"}}}", User.class);
        System.out.println(user);

        TestClass tc = new TestClass(22, "value");
        System.out.println(ijsoNconvertor.objectToJson(tc));
        try {
           // TestClass tc2 = ijsoNconvertor.classFromJson("{\"age\":22,\"test\":\"value\"}", TestClass.class);
           // System.out.println(tc2);
            User usert = ijsoNconvertor.classFromJson("{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Toyota\",\"cl\":{\"test\":\"Test\"}}}", User.class);
            System.out.println(usert);
        } catch (IncorrectClassException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

//        System.out.println("witout brakes:");
//        String witoutbrakes =  new JSONconvertorImplement().psrserObject(
//                "{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Toyota\",\"cl\":{\"test\":\"Test\"}}}");
//        System.out.println(witoutbrakes);
//        System.out.println("classFromJson:");
//        try {
//            System.out.println(new JSONconvertorImplement().fromJson(witoutbrakes, User.class));
//        } catch (IncorrectClassException e) {
//            e.printStackTrace();
//        }
//        try {
//            User formJsonUser = ijsoNconvertor.classFromJson("{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Toyota\",\"cl\":{\"test\":\"Test\"}}}", User.class);
//        } catch (IncorrectClassException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        /*System.out.println("test parseField:");
        String testParsFild = "\"field\":Vasa,";
        System.out.println(testParsFild);
        System.out.println(new JSONconvertorImplement().psrserFieldName(testParsFild));
        System.out.println("test parseValue:");
        System.out.println(new JSONconvertorImplement().parserFieldValue(testParsFild));

        System.out.println("JSON first field as class: ");
        FirstFieldAsClass firstFieldAsClass = new FirstFieldAsClass(new TestClass("test class"), "text");
        System.out.println(ijsoNconvertor.objectToJson(firstFieldAsClass));
        String res = ijsoNconvertor.objectToJson(firstFieldAsClass);
        //System.out.println(new JSONconvertorImplement().parserFieldValue(res));
        System.out.println(new JSONconvertorImplement().classFromJson(res));
        System.out.println("=============================================");
        System.out.println("Parsing test");
        String testsStr = "{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Toyota\",\"cl\":{\"test\":\"Test\"}}}";
        System.out.println(testsStr);
        System.out.println("delete brackets");
        testsStr = new JSONconvertorImplement().psrserObject(testsStr);
        System.out.println(testsStr);
        System.out.println("parsing");
        System.out.println(new JSONconvertorImplement().classFromJson(testsStr));
        // System.out.println(new JSONconvertorImplement().psrserArray("{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Lexus\"}}"));
        // System.out.println(gson.objectToJson(cl.getName()));
        // System.out.println(gson.objectToJson(cl.getName()));
//        String x = "\\";
//        String y = "\"";
//        System.out.println("It's Windows path: " + y + "C:" + x + "Program Files" + x + "Java" + x + "jdk1.7.0" + x + "bin" + y);
//        System.out.println("It's Java string: " + x + y + "C:" + x + x + "Program Files" + x + x + "Java" + x + x + "jdk1.7.0" + x + x + "bin" + x + y);*//*
*/
    }
}
