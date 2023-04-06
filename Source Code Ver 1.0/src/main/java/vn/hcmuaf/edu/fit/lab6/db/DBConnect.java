package vn.hcmuaf.edu.fit.lab6.db;

import java.sql.*;

public class DBConnect {
    private static DBConnect dbConnect;
    private static Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lt_web?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Statement connect() throws ClassNotFoundException, SQLException {
        if(connection == null || connection.isClosed()){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER,PASS);
        }
        return connection.createStatement();
    }

}
