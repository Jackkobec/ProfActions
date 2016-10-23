package gson_actions;

/**
 * Created by Jack on 22.10.2016.
 */
public interface IJSONconvertor {

    String objectToJson(Object obj);

    <T> T classFromJson(String str, Class<T> cl);
}
