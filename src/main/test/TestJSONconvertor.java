import com.google.gson.Gson;
import gson_actions.IJSONconvertor;
import gson_actions.JSONconvertorImplement;
import org.junit.Before;
import org.junit.Test;
import roles.Car;
import roles.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * Created by Jack on 22.10.2016.
 */
public class TestJSONconvertor {
    User us;
    IJSONconvertor ijsonConvertor;
    Gson gson;

    @Before
    public void initData() {
        us = new User("Vasa", 22, new int[]{1, 2, 3}, new Car("Lexus"));
        ijsonConvertor = new JSONconvertorImplement();
        gson = new Gson();
    }

    @Test
    public void testToJSONposetive() {
        assertEquals(gson.toJson(us), ijsonConvertor.objectToJson(us));
    }
    @Test
    public void testToJSONnegative() {
        assertFalse("{\"name\":\"Vasa\",\"age\":22,\"mas\":[1,2,3],\"car\":{\"model\":\"Test Failer\"}}".equals(ijsonConvertor.objectToJson(us)));
    }


}
