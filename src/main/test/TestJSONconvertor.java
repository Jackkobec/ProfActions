import com.google.gson.Gson;
import gson_actions.IJSONconvertor;
import gson_actions.JSONconvertorImplement;
import org.junit.Before;
import org.junit.Test;
import roles.User;

import static org.junit.Assert.assertEquals;


/**
 * Created by Jack on 22.10.2016.
 */
public class TestJSONconvertor {
    User us;
    IJSONconvertor ijsonConvertor;
    Gson gson;

    @Before
    public void initData() {
        us = new User("Vasa", 22, new int[]{1, 2, 3});
        ijsonConvertor = new JSONconvertorImplement();
        gson = new Gson();
    }

    @Test
    public void testToJSON() {
        assertEquals("{\"name\":\"Vasa\",\"age\":22}", ijsonConvertor.toJson(us));
    }


}
