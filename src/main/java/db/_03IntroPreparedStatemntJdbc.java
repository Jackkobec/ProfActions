package db;

import java.sql.*;

/**
 * Created by serhii on 17.01.16.
 */
public class _03IntroPreparedStatemntJdbc {

    public static final String URL = "jdbc:mysql://localhost:3306/ACP11";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String ALL_USERS_QUERY = "SELECT id,name,amount,birth,phone FROM users";

    public static void main(String[] args) throws ClassNotFoundException {
        // load driver
        Class.forName("com.mysql.jdbc.Driver");
        User user = new User(-1, "new user", 10000, new java.util.Date(), "+3343433242");


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name,birth,phone,amount) VALUES (?,?,?,?)");){

            preparedStatement.setString(1,user.getName());
            preparedStatement.setDate(2,new Date(user.getDate().getTime()));
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setDouble(4,user.getAmount());

            preparedStatement.execute();





        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

}
