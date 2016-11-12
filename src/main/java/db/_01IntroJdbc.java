package db;

import java.sql.*;

/**
 * Created by serhii on 17.01.16.
 */
public class _01IntroJdbc {

    public static final String URL = "jdbc:mysql://localhost:3306/ACP11";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String ALL_USERS_QUERY = "SELECT id,name,amount,birth,phone FROM users";

    public static void main(String[] args) throws ClassNotFoundException {
        // load driver
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.
                    getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ALL_USERS_QUERY);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("amount");
                Date date = resultSet.getDate("birth");
                String phone = resultSet.getString("phone");

                System.out.println(new User(id,name,salary,date,phone));
            }





        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
