import com.google.gson.Gson;
import gson_actions.IJSONconvertor;
import gson_actions.JSONconvertorImplement;
import roles.Car;
import roles.User;

/**
 * Created by Jack on 23.10.2016.
 */
public class TestConvertor {
    public static void main(String[] args) {
        IJSONconvertor ijsoNconvertor = new JSONconvertorImplement();
        User us = new User("Vasa", 22, new int[]{1,2,3}, new Car("Lexus"));

        Gson gson = new Gson();
//        String jj = "HiThere";
//        Class cl = us.getClass();
        int[] mas = {1,2,3};
        System.out.println();
        System.out.println(gson.toJson(us));
        System.out.println(ijsoNconvertor.objectToJson(us));


        // System.out.println(gson.objectToJson(cl.getName()));
        // System.out.println(gson.objectToJson(cl.getName()));
//        String x = "\\";
//        String y = "\"";
//        System.out.println("It's Windows path: " + y + "C:" + x + "Program Files" + x + "Java" + x + "jdk1.7.0" + x + "bin" + y);
//        System.out.println("It's Java string: " + x + y + "C:" + x + x + "Program Files" + x + x + "Java" + x + x + "jdk1.7.0" + x + x + "bin" + x + y);

    }
}
