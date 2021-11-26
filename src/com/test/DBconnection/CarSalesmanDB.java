package com.test.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarSalesmanDB {

    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Connects to the driver
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost/carsalesman","root", "DFndi*@&8f*djHIF8&*");
        System.out.println("Connection complete!");

    }
}
