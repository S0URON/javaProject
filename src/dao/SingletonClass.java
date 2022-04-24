package dao;

import java.sql.*;

public class SingletonClass {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_1", "root", "");
            System.out.println("connection etablie");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
