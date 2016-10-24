package roles;

/**
 * Created by Jack on 23.10.2016.
 */
public class Car {
    public String model;
    public TestClass cl;


    public Car(String model) {
        this.model = model;
    }

    public Car(String model, TestClass cl) {
        this.model = model;
        this.cl = cl;
    }

    public Car(String model, Car car) {
        this.model = model;

    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", cl=" + cl +
                '}';
    }
}
