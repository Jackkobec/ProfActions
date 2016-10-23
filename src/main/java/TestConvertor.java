import com.google.gson.Gson;
import reflection_actions.ConvertorImplement;
import reflection_actions.IConvertor;
import roles.User;

/**
 * Created by Jack on 23.10.2016.
 */
public class TestConvertor {
    public static void main(String[] args) {
        IConvertor iConvertor = new ConvertorImplement();
        User us = new User("Vasa", 22);

        String res = iConvertor.toString(us);
        System.out.println(res);
        Gson gson = new Gson();
//        String jj = "HiThere";
//        Class cl = us.getClass();
        int[] mas = {1,2,3};

        System.out.println(gson.toJson(mas));
        // System.out.println(gson.toJson(cl.getName()));
        // System.out.println(gson.toJson(cl.getName()));
//        String x = "\\";
//        String y = "\"";
//        System.out.println("It's Windows path: " + y + "C:" + x + "Program Files" + x + "Java" + x + "jdk1.7.0" + x + "bin" + y);
//        System.out.println("It's Java string: " + x + y + "C:" + x + x + "Program Files" + x + x + "Java" + x + x + "jdk1.7.0" + x + x + "bin" + x + y);

    }
}
