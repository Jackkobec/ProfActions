package roles;

/**
 * Created by Jack on 24.10.2016.
 */
public class FirstFieldAsClass {
    public TestClass testClass;
    public String str;

    public FirstFieldAsClass() {
    }

    public FirstFieldAsClass(TestClass testClass, String str) {
        this.testClass = testClass;
        this.str = str;
    }

    @Override
    public String toString() {
        return "FirstFieldAsClass{" +
                "testClass=" + testClass +
                ", str='" + str + '\'' +
                '}';
    }
}
