package com.test.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static com.test.controller.DashBoardController.customer;
import static com.test.controller.DashBoardController.index;

public class SalesProcessDAO {

    public static boolean loginSuccess(String email, String password) throws SQLException {
        String query = ("SELECT * FROM carsalesman.login " +
                "WHERE username='" + email + "'"
                + "AND password='" + password + "'");

        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        return resultset.next();
    }

    public static void submitForm(Customer choice, String updateOrInsert) throws SQLException {
        CarSalesmanDB.dbExecuteUpdate(updateOrInsert); // New or updated  Customer
        customer.add(choice);
        index = customer.indexOf(choice);
    }

    public static void followUpINFO() {
        //TODO: must place certain customer data into the text field. solid SQL is needed and solid DB arrangement
    }

    /**
     * Use list DS instead
     **/

    public static int calculatorDefault() throws SQLException {
        // TODO: preapproval loan and interest rate based on grossly. grossly from DS. Car price from DB
        //pulling from database
        Customer temp = customer.get(index);

        String query = "SELECT price" +
                "FROM carsalesman.inventory" +
                "WHERE model = '" + temp.getCarType() + "';";

        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);

        int price = 0;
        while (resultset.next()) {
            price = resultset.getInt("price");
        }
        // need strings of the customer info
        // TODO: loan stored into DB (probably different function)
        return price;
    }

    public static ResultSet contractSection1() throws SQLException {
        // TODO: populate contract with customer info, loan and chosen car details
        Customer temp = customer.get(index);
        String cust_info = "SELECT full_name, address1, city, state, zip, phone_number"
                         + "FROM carsalesman.customer_info" +
                "WHERE ssn ='" + temp.getSsn() + "';";

        return CarSalesmanDB.dbExecuteQuery(cust_info);
    }

    public static ResultSet contractSection2() throws SQLException {
        Customer temp = customer.get(index);

        String inventory = "SELECT * FROM carsalesman.inventory" +
                "WHERE model = '" + temp.getCarType() + "';";

        return CarSalesmanDB.dbExecuteQuery(inventory);
    }

    public static void inventory(LinkedList<String> leadManager) throws SQLException {
        // TODO: pull car data into the customers table joint table
        String query = "SELECT CONCAT(inventory.make, ' ', inventory.model , ' ', inventory.year) " +
                "AS product FROM carsalesman.inventory;";
        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        while (resultset.next()) {
            leadManager.add(resultset.getString("product"));
        }
    }
}
