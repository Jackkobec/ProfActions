package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by serhii on 17.01.16.
 */

public class _01IntroInsertJdbc {

    public static final String URL = "jdbc:mysql://localhost:3306/ACP11";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String ALL_USERS_QUERY = "SELECT id,name,amount,birth,phone FROM users";

    public static void main(String[] args) throws ClassNotFoundException {
        // load driver
        Class.forName("com.mysql.jdbc.Driver");
        User user = new User(-1, "new user", 10000, new java.util.Date(), "+33434343434");


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();){
            connection.setAutoCommit(false);
            statement.executeUpdate(
                    String.format("INSERT INTO users(name,birth,phone,amount) VALUES ('%1$s','%2$ty-%2$tm-%2$td','%3$s',%4$.2f)",
                            user.getName(),
                            user.getDate(),
                            user.getPhone(),
                            user.getAmount()));
            connection.commit();







        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

}
