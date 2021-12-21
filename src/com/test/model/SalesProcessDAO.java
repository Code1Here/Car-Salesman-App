package com.test.model;

import java.sql.*;
import java.util.LinkedList;

import static com.test.controller.DashBoardController.customerList;
import static com.test.controller.DashBoardController.index;
import static com.test.controller.DashBoardController.triggerInit;
import static com.test.controller.DashBoardController.trigger2;

public class SalesProcessDAO { // Data Access Object

    static boolean signingIn = true;
    public static String status; // On-duty or Off-duty
    public static String stamp; // employee_id

    public static boolean loginSuccess(String email, String password) throws SQLException {
        String query = "SELECT * FROM carsalesman.login \n" +
                "WHERE username='" + email + "'\n"
                + "AND password='" + password + "'";

        ResultSet temp = CarSalesmanDB.dbExecuteQuery(query);

        stamp = null;
        while (temp.next()) {
            stamp = temp.getString("employee_id");
        }

        if (stamp != null) {
            // Mark log in/out for employer table
            status = "UPDATE carsalesman.login\n" +
                    "SET time_log = 'clocked-in'\n" +
                    "WHERE employee_id = '" + stamp + "'";

            SalesProcessDAO.punchIn_punchOut(stamp, status);
        } else
            System.out.println("Trouble logging in and updating status");


        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        return resultset.next(); // return true if login is a success
    }


    public static void punchIn_punchOut(String stamp, String status) throws SQLException {
        if (signingIn) {
            CarSalesmanDB.dbExecuteUpdate(status); // Update status for employee table

            String log = "INSERT INTO carsalesman.login_history (employee_id, status)\n" +
                    "VALUES ('" + stamp + "', 'on-duty')";

            CarSalesmanDB.dbExecuteUpdate(log); // Update log to track employee
        } else {
            status = "UPDATE carsalesman.login\n" +
                    "SET time_log = 'clocked-out'\n" +
                    "WHERE employee_id = '" + stamp + "'";
            CarSalesmanDB.dbExecuteUpdate(status);

            String log = "INSERT INTO carsalesman.login_history (employee_id, status)\n" +
                    "VALUES ('" + stamp + "', 'off-duty')";

            CarSalesmanDB.dbExecuteUpdate(log);
        }
        signingIn = !signingIn;

    }

    public static void inventory(LinkedList<String> VehicleManager) throws SQLException {
        String query = "SELECT CONCAT(inventory.make, ' ', inventory.model , ' ', inventory.year) \n" +
                "AS product FROM carsalesman.inventory;";
        ResultSet resultset = CarSalesmanDB.dbExecuteQuery(query);
        while (resultset.next()) {
            VehicleManager.add(resultset.getString("product"));
        }
    }

    public static void submitForm(Customer choice, String updateOrInsert) throws SQLException {
        CarSalesmanDB.dbExecuteUpdate(updateOrInsert); // New or updated  Customer
        customerList.add(choice);
        index = customerList.indexOf(choice);
        trigger2 = triggerInit = true;
    }

    public static ResultSet populateFollowUp() throws SQLException {
        String query = "SELECT full_name, grossly, phone_number, ssn\n" +
                "FROM carsalesman.customer_info";

        return CarSalesmanDB.dbExecuteQuery(query);
    }

    public static int calculatorDefault() throws SQLException {
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
        return price;
    }

    public static ResultSet contractSection1() throws SQLException {
        Customer temp = customerList.get(index);
        String cust_info = "SELECT full_name, address1, city, state, zip, phone_number\n"
                + "FROM carsalesman.customer_info\n" +
                "WHERE ssn ='" + temp.getSsn() + "';";

        return CarSalesmanDB.dbExecuteQuery(cust_info);
    }

    public static ResultSet contractSection2() throws SQLException {
        Customer temp = customerList.get(index);

        String inventory = "SELECT * FROM carsalesman.inventory\n" +
                "WHERE model = '" + temp.getCarType() + "';";

        return CarSalesmanDB.dbExecuteQuery(inventory);
    }

    public static void finalization(String finalSale) throws SQLException {
        String contractSale =
                "INSERT INTO carsalesman.invoice (invoice, date, total, loan, deposit, banknote, vin, ssn, employee_id)" +
                "VALUES ('" + finalSale + stamp + "')";

        // TODO: At this point I can, if I want, remove the customer off the list of leads by popping customer off the array
        System.out.println(stamp);
        CarSalesmanDB.dbExecuteUpdate(contractSale);
    }
}