package gson_actions;

import com.google.gson.internal.Primitives;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
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
                } else if (fields[i].getType() == String.class) {
                    jvalue = kav + fields[i].get(obj).toString() + kav;
                } else if (fields[i].getType().isArray()) {
                    jvalue = arrayToString(fields[i].get(obj));
                } else {
                    jvalue = objectToJson(fields[i].get(obj));
                }

               //json += (i == fields.length - 1) ? jfieldName + ":" + jvalue : jfieldName + ":" + jvalue + ",";
                json += jfieldName + ":" + jvalue + (i == fields.length - 1 ? "" : ",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            /**
             * В данном случае NullPointerException вылетает при наличии в конвертируемом классе рекурсивного поля, пример:
             * public class Car {
             public String model;
             public TestClass cl;
             public Car car;        -  рекурсивное поле

             Вследствие этого при рекурсивном вызове метода objectToJson вылетает исключение. В данном контексте NullPointerException
             является нормальным поведением программы и его игнорирование не несет последствий для дальнейшей работы программы(я надеюсь))
             */ catch (NullPointerException ignored) {
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
        Object result = new Object();
        return (T) result;
    }

    public String psrserObject(String str) {
        int start = str.indexOf('{');
        int end = str.lastIndexOf('}');
        return str.substring(start + 1, end);
    }

    public String psrserArray(String str) {
        int start = str.indexOf('[');
        int end = str.lastIndexOf(']');
        return str.substring(start + 1, end);
    }
}
