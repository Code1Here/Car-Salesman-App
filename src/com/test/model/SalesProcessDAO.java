package com.test.model;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class SalesProcessDAO {

    public static boolean loginSuccess(String email, String password) throws SQLException {
        String query = ("SELECT * FROM login " +
                        "WHERE username='" +email+ "' AND password='" +password+ '"');

        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        return resultset.next();
    }
}