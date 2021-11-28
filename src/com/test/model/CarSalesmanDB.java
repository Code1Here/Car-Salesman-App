package com.test.model;

import java.sql.*;

public class CarSalesmanDB {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/carsalesman";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "DFndi*@&8f*djHIF8&*";
    // Connect to the localdatabase

    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            System.out.println("Driver Issues");
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException exception) {
            System.out.println("Could not get connection to mysql");
        }

        System.out.println("Connection might be null");
        return connection;
    }
}