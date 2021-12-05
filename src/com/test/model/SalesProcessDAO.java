package com.test.model;

import java.sql.*;

public class SalesProcessDAO {

    public static boolean loginSuccess(String email, String password) throws SQLException {
        String query = ("SELECT * FROM login " +
                        "WHERE username='" + email +
                      "' AND password='" + password + "'");

        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        return resultset.next();
    }

    public static void submitForm(String ssn, String updateOrInsert) throws SQLException {
        CarSalesmanDB.dbExecuteUpdate(updateOrInsert); // New or updated  Customer
    }

    public static void followUpINFO() {
        //must place certain customer data into the text field. solid SQL is needed and solid DB arrangement
    }

    public static void calculatorDefault() {
        // preapproval loan and interest rate based on grossly loaded into the window

        // loan stored into DB
    }

    public static void contract() {
        // populate contract with customer info, loan and chosen car details
    }

    public static void carData() {
        // pull car data into the customers table joint table
    }
}
