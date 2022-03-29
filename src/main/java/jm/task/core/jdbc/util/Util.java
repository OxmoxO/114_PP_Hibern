package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.NonRegisteringDriver;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/OXANA";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ZaStalina53!";
    private Connection connection;

    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Успешное соединение !!!");
            }
        } catch (SQLException ex) {
            System.err.println("Драйвер утерян !!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден !!!");
        }
    }
    public Connection getConnection() {
        return connection;
    }
}

