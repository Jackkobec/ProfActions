package reflection;

import ua.artcode.home.week4.ForSave;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 26.12.15.
 */
public class ObjectSrtConverterImpl implements ObjectStrConverter {
    @Override
    public String toString(Object obj) {
        Class cl = obj.getClass();

        String content = "type=" + cl.getName() + "\n";

        for(Field field : cl.getFields()){
            try {
                if(field.isAnnotationPresent(ForSave.class)){
                    content += field.getName() + "=" + field.get(obj) + "\n";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return content;
    }

    @Override
    public Object toObject(String src) {
        Map<String,String> nameValueMap = getFieldNameValueMap(src);

        try {
            Class cl = Class.forName(nameValueMap.remove("type"));

            Object newObj = cl.newInstance();

            for (String name : nameValueMap.keySet()) {
                try {
                    Field field = cl.getField(name);
                    if(field.isAnnotationPresent(ForSave.class)){
                        field.set(newObj, convertTo(nameValueMap.get(name), field.getType()));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            return newObj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object convertTo(String value, Class<?> type) {
        if(type == int.class){
            return Integer.parseInt(value);
        } else if(type == double.class){
            return Double.parseDouble(value);
        }
        return value;
    }

    private static Map<String,String> getFieldNameValueMap(String src){
        String[] parts = src.split("\n");
        Map<String,String> map = new HashMap<>();

        for (String part : parts) {
            String[] nameValue = part.split("=");
            map.put(nameValue[0], nameValue[1]);
        }

        return map;
    }
}
