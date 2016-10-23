package reflection_actions;


import java.lang.reflect.Field;

/**
 * Created by Jack on 23.10.2016.
 */
public class ConvertorImplement implements IConvertor {
    @Override
    public String toString(Object obj) {
        StringBuilder sb = new StringBuilder();

        Class cl = obj.getClass();
        String result = cl.getName();

        sb.append(cl.getName()).append("\n");
        Field[] fields = cl.getFields();

        for (Field field : fields) {
            sb.append(field.toString()).append("\n");
        }
        return sb.toString();
    }
}
