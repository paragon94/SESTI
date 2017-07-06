package main.conf.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDatabase {

    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DATABASE = "SESTI";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "krzysiu";

    protected Connection connection;
    protected Statement statement;

    public Connection openConnection() {
        connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + DATABASE + "?useUnicode=true&characterEncoding=utf-8", USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("Nie połączono z bazą danych" + e);
        }
    }
}
