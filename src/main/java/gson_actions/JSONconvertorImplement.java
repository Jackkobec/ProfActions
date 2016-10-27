package gson_actions;

import sun.reflect.generics.tree.FieldTypeSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public <T> T classFromJson(String json, Class<T> cl) throws IncorrectClassException, IllegalAccessException, InstantiationException {
        String withoutBrackes = psrserObject(json);
        return fromJson(withoutBrackes, cl);
    }

    public <T> T fromJson(String jsonWithoutBrackets, Class<T> cl) throws IncorrectClassException, IllegalAccessException, InstantiationException {
        Field[] fields = cl.getDeclaredFields();
        T t = cl.newInstance();
        String res = "";
        for (int i = 0; i < fields.length; ++i) {
            //res += fields[i].getName() + "=" + fromJson(jsonWithoutBrackets, fields[i].getName()) + ((i == fields.length - 1) ? "" : ", ");
            t = fromJson(jsonWithoutBrackets, fields[i].getName(), fields[i].getType(), fields[i], t);
        }
        return t;
    }

    public <T> T fromJson(String jsonWithoutBrackets, String fieldName, Type type, Field field, T t) throws IncorrectClassException, IllegalAccessException {
        if (!jsonWithoutBrackets.contains(fieldName)) {
            throw new IncorrectClassException("Поле не найдено. Скорее всего передан неправильный класс.");
        } else {
            int start = jsonWithoutBrackets.indexOf(fieldName);
            int newStart = start + fieldName.length();
            return parserFieldValue22(jsonWithoutBrackets.substring(newStart), type, field, t);
        }
    }

    public <T> T parserFieldValue22(String str, Type type, Field field, T t) throws IllegalAccessException {
        int start = str.indexOf(':');
        int end = 0;
        if (str.contains(",") && str.substring(str.indexOf(",") + 1).isEmpty()) {
            end = str.indexOf(',');
        } else
            end = (str.substring(start + 2).indexOf('"')) + 3; //+3 компенсирует отнятое сабстрингом

        if (type == String.class) {
            field.set(t, str.substring(start + 2, end));
        } else if (type == int.class) {
            field.set(t, Integer.parseInt(str.substring(start + 1, end - 1).replaceAll(",", "")));
        } else if (type == int[].class) {
            field.set(t, psrserArrayIntDigits(str.substring(start + 2, end - 2)));
        } else if (type == Arrays.class) {
            field.set(t, psrserArray(str.substring(start + 1)));
        } else {
            // parserFieldValue22(str.substring(start +2, end), type, field, t);
        }
        return t;
    }

    public int[] psrserArrayIntDigits(String str) {
        return Arrays.stream(str.replaceAll(",", " ").split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public String psrserObjectValue(String str) {
        int start = str.indexOf('{');
        int end = str.lastIndexOf('}');
        return str.substring(start, end) + "}";
    }

    public String psrserObject(String str) {
        int start = str.indexOf('{');
        int end = str.lastIndexOf('}');
        return str.substring(start + 1, end);
    }

    public String psrserArray(String str) {
        int start = str.indexOf('[');
        int end = str.lastIndexOf(']');
        return "[" + str.substring(start + 1, end) + "]";
    }

    public String psrserFieldName(String str) {
        int start = str.indexOf('"');
        int end = str.indexOf(':');
        return str.substring(start + 1, end - 1);
    }

//    public String parserFieldValue(String str) {
//        int start = str.indexOf(':');
//        int end = str.indexOf(',');
//        return str.substring(start + 1, end);
//    }
//    public String fromJson(String str) {
//        //String res = "";
//        int fieldEnd = fieldEndDetector(str);
//        int arrayEndDetector = arrayEndDetector(str);
//        if (str.substring(0, 1).contains(" ")) {
//            fromJson(str.substring(1));
//        }
//
//        if (str.substring(0, 1).contains("\"")) {
//            return psrserFieldName(str) + "=" + parserFieldValue(str) + fromJson(str.substring(fieldEnd + 1));
//        } else if (str.substring(0, 1).contains("[")) {
//            return psrserArray(str);// + ", " + fromJson(str.substring(arrayEndDetector+1));
//        } else if (str.substring(0, 1).contains("{")) {
//            return psrserObject(str) + psrserObject(str);
//        }
////       fromJson(str.substring(fieldEnd-1));
//        return "";//fromJson(str.substring(fieldEnd));
//    }
//public int fieldEndDetector(String str) {
//    return new Integer(str.indexOf("\""));
//}
//
//    public int arrayEndDetector(String str) {
//        return new Integer(str.indexOf(']'));
//    }
    /**
     * Working version fromJson
     * return  parsed string from json
     */
   /* public <T> T classFromJson(String json, Class<T> cl) throws IncorrectClassException, IllegalAccessException, InstantiationException {
        String classname = cl.getName();
        String withoutBrackes = psrserObject(json);
        T t = cl.newInstance();
        return (T) (classname + "{"+ fromJson(withoutBrackes, cl) + "}");
    }

    public <T> String fromJson(String jsonWithoutBrackets, Class<T> cl) throws IncorrectClassException {
        Field[] fields = cl.getDeclaredFields();
        String res = "";
        for (int i = 0; i < fields.length; ++i) {

            res += fields[i].getName() + "=" + fromJson(jsonWithoutBrackets, fields[i].getName()) + ((i == fields.length - 1) ? "" : ", ");
        }
        return res;
    }
    public String fromJson(String jsonWithoutBrackets, String fieldName) throws IncorrectClassException {
        if (!jsonWithoutBrackets.contains(fieldName)) {
            throw new IncorrectClassException("Поле не найдено. Скорее всего передан неправильный класс.");
        } else {
            int start = jsonWithoutBrackets.indexOf(fieldName);
            int newStart = start + fieldName.length();
            return parserFieldValue(jsonWithoutBrackets.substring(newStart));
        }
    }

    public String parserFieldValue(String str) {
        String res = "";
        int start = str.indexOf(':');
        int end = str.indexOf(',');
        if (str.substring(start, start + 2).contains("\"")) {
            res += str.substring(start + 1, end);
        } else if (str.substring(start, start + 2).contains("[")) {
            res += psrserArray(str.substring(start + 1));
        } else if (str.substring(start, start + 2).contains("{")) {
            res += psrserObjectValue(str.substring(start + 1));
        } else if (str.substring(start, start + 2).contains("")) {
            res += str.substring(start + 1, end);
        }
        return res;//str.substring(start + 1, end);
    }



    public String psrserObjectValue(String str) {
        int start = str.indexOf('{');
        int end = str.lastIndexOf('}');
        return str.substring(start, end) + "}";
    }

    public String psrserObject(String str) {
        int start = str.indexOf('{');
        int end = str.lastIndexOf('}');
        return str.substring(start + 1, end);
    }

    public String psrserArray(String str) {
        int start = str.indexOf('[');
        int end = str.lastIndexOf(']');
        return "[" + str.substring(start + 1, end) + "]";
    }

    public String psrserFieldName(String str) {
        int start = str.indexOf('"');
        int end = str.indexOf(':');
        return str.substring(start + 1, end - 1);
    }*/
}
























