package gson_actions;

import com.google.gson.Gson;

import java.lang.reflect.Field;

/**
 * Created by Jack on 22.10.2016.
 */
public class JSONconvertorImplement implements IJSONconvertor {

    public String toJson(Object obj) {
        Gson gson = new Gson();
        Class cl = obj.getClass();

        StringBuilder sb = new StringBuilder();
        String jsonClassStart = "{";
        String jsonClassEnd = "}";
        String kav = "\"";
        String json = "";
        String resJson = "";
        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {

            try {
                String jfieldName = kav + fields[i].getName() + kav;
                String jvalue = ""; //fields[i].getType().isPrimitive() ? fields[i].get(obj).toString() : kav + fields[i].get(obj).toString() + kav;

                if (fields[i].getType().isPrimitive()) {
                    jvalue = fields[i].get(obj).toString();
                } else if (fields[i].getType().isInstance("")) {
                    jvalue = kav + fields[i].get(obj).toString() + kav;
                } else if (fields[i].getType().isArray()) {
                    jvalue = "[" + fields[i].get(obj).toString() + "]";
                } else {
                    jvalue = "{" + fields[i].get(obj).toString() + "}";
                }

                if (i == fields.length - 1) {
                    json += jfieldName + ":" + jvalue;
                } else {
                    json += jfieldName + ":" + jvalue + ",";
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            resJson = jsonClassStart + json + jsonClassEnd;
        }
        return resJson;
    }


    public <T> T classFromJson(String str, Class<T> cl) {

        // возвравщает отпарсенный json в инстанс класса <T>
        return null;
    }
}
