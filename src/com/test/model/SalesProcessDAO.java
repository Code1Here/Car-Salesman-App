package com.test.model;

import java.sql.*;
import java.util.LinkedList;

import static com.test.controller.DashBoardController.customerList;
import static com.test.controller.DashBoardController.index;

public class SalesProcessDAO {

    public static boolean loginSuccess(String email, String password) throws SQLException {
        String query = "SELECT * FROM carsalesman.login \n" +
                        "WHERE username='" + email + "'\n"
                      + "AND password='" + password + "'";

        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        return resultset.next();
    }

    public static void submitForm(Customer choice, String updateOrInsert) throws SQLException {
        CarSalesmanDB.dbExecuteUpdate(updateOrInsert); // New or updated  Customer
        customerList.add(choice);
        index = customerList.indexOf(choice);
    }

    public static ResultSet populateFollowUp() throws SQLException {
        String query = "SELECT full_name, grossly, phone_number, ssn\n" +
                       "FROM carsalesman.customer_info";

       return CarSalesmanDB.dbExecuteQuery(query);
    }

    /**
     * Use list DS instead
     **/

    public static int calculatorDefault() throws SQLException {
        // TODO: preapproval loan and interest rate based on grossly. grossly from DS. Car price from DB
        //pulling from database
        Customer temp = customerList.get(index);

        String query = "SELECT price\n" +
                "FROM carsalesman.inventory\n" +
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
        Customer temp = customerList.get(index);
        String cust_info = "SELECT full_name, address1, city, state, zip, phone_number\n"
                         + "FROM carsalesman.customer_info" +
                "WHERE ssn ='" + temp.getSsn() + "';";

        return CarSalesmanDB.dbExecuteQuery(cust_info);
    }

    public static ResultSet contractSection2() throws SQLException {
        Customer temp = customerList.get(index);

        String inventory = "SELECT * FROM carsalesman.inventory\n" +
                "WHERE model = '" + temp.getCarType() + "';";

        return CarSalesmanDB.dbExecuteQuery(inventory);
    }

    public static void inventory(LinkedList<String> VehicleManager) throws SQLException {
        // TODO: pull car data into the customers table joint table <- old
        String query = "SELECT CONCAT(inventory.make, ' ', inventory.model , ' ', inventory.year) \n" +
                "AS product FROM carsalesman.inventory;";
        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        while (resultset.next()) {
            VehicleManager.add(resultset.getString("product"));
        } // TODO: handled with an array
    }
}
