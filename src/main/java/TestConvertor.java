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
    }
}
