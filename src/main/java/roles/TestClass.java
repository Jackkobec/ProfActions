package roles;

/**
 * Created by Jack on 24.10.2016.
 */
public class TestClass {
    public int age;
    public String test;

    public TestClass() {
    }

    public TestClass(int age, String test) {
        this.age = age;
        this.test = test;
    }


    @Override
    public String toString() {
        return "TestClass{" +
                "age=" + age +
                ", test='" + test + '\'' +
                '}';
    }
}
