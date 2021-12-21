package com.test.controller;

import com.test.model.Customer;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class ClientDetailsController extends BaseController {
    public ClientDetailsController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private TextField addr1;

    @FXML
    private TextField addr2;

    @FXML
    private TextField city;

    @FXML
    private TextField dob;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private TextField grossly;

    @FXML
    private TextField number;

    @FXML
    private TextField sex;

    @FXML
    private TextField ssn;

    @FXML
    private TextField state;

    @FXML
    private TextField zip;

    @FXML
    void finished(ActionEvent event) throws IOException {
        String newCustomer = "INSERT INTO customer_info(ssn, full_name, sex, phone_number, email, address1, address2, " +
                "city, state, zip, grossly, dob) VALUES('" + ssn.getText().trim() + "', '" + fullName.getText().trim() + "', '" +
                sex.getText().trim() + "', '" + number.getText().trim() + "', '" + email.getText().trim() + "', '" + addr1.getText().trim() +
                "', '" + addr2.getText().trim() + "', '" + city.getText().trim() + "', '" + state.getText().trim() + "', '" + zip.getText().trim() +
                "', '" + grossly.getText().trim() + "', '" + dob.getText().trim() + "');";
        try {
            Customer choice = new Customer(fullName.getText().trim(), Integer.parseInt(grossly.getText().trim()), number.getText().trim(), ssn.getText().trim());
            SalesProcessDAO.submitForm(choice, newCustomer);
            Stage stage = (Stage) ssn.getScene().getWindow();
            viewFactory.closeStage(stage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}