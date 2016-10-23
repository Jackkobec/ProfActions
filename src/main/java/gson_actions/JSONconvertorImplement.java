package gson_actions;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by Jack on 22.10.2016.
 */
public class JSONconvertorImplement implements IJSONconvertor {

    public String objectToJson(Object obj) {

        Class cl = obj.getClass();

        String json = "";
        String jsonClassStart = "{";
        String jsonClassEnd = "}";

        String kav = "\"";
        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {

            try {
                String jfieldName = kav + fields[i].getName() + kav;
                String jvalue = "";

                if (fields[i].getType().isPrimitive()) {
                    jvalue = fields[i].get(obj).toString();
                } else if (fields[i].getType().isInstance("")) {
                    jvalue = kav + fields[i].get(obj).toString() + kav;
                } else if (fields[i].getType().isArray()) {
                    jvalue = arrayToString(fields[i].get(obj));
                } else {
                    jvalue = objectToJson(fields[i].get(obj));
                }

                json += i == fields.length - 1 ? jfieldName + ":" + jvalue : jfieldName + ":" + jvalue + ",";

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonClassStart + json + jsonClassEnd;
    }

    public static String arrayToString(Object obj) {
        Class cl = obj.getClass();
        if (cl.isArray()) {
            return Arrays.toString((int[]) obj).replaceAll(" +", "");
        }
        return null;
    }

    public <T> T classFromJson(String str, Class<T> cl) {
        // возвравщает отпарсенный json в инстанс класса <T>
        return null;
    }
}
