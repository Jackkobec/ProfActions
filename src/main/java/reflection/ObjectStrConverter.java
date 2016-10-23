package reflection;

/**
 * Created by serhii on 26.12.15.
 */
public interface ObjectStrConverter {

    String toString(Object obj);

    Object toObject(String src);

}
