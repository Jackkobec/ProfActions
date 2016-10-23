package reflection;

import ua.artcode.home.week4.Car;
import ua.artcode.home.week4.User;

public class _01Intro {


    public static void main(String[] args) throws NoSuchMethodException {

        String info = ReflectionUtils.getTypeInfo(new String());
        System.out.println(info);
        User user = new User(12, 33, "Oleg", "+380933332312");
        System.out.println(
                ReflectionUtils.getFieldsValue(
                        user));

        System.out.println(ReflectionUtils.getTypeInfo(user));

        System.out.println(ReflectionUtils.callMethod(
                user, "setCar", new Car("2122", "toyota", 250)));

        System.out.println(user);


        try {
            Class cl = Class.forName("ua.artcode.home.week4.Car");
            cl.getTypeParameters();
            Car car = (Car) cl.newInstance(); // must have default constructor
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
