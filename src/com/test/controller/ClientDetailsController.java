package com.test.controller;

import com.test.model.CarSalesmanDB;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ClientDetailsController extends BaseController implements Initializable {
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
    void finished(ActionEvent event) {
        String newCustomer = "INSERT INTO customer_info(ssn, full_name, sex, phone_number, email, address1, address2, " +
                "city, state, zip, grossly, dob) VALUES('"+ssn.getText().trim()+"', '"+fullName.getText().trim()+"', '"+
                sex.getText().trim()+"', '"+number.getText().trim()+"', '"+email.getText().trim()+"', '"+addr1.getText().trim()+
                "', '"+addr2.getText().trim()+"', '"+city.getText().trim()+"', '"+state.getText().trim()+"', '"+zip.getText().trim()+
                "', '"+grossly.getText().trim()+"', '"+dob.getText().trim()+"');";
//        String[] newCustomer = {ssn.getText().trim(), fullName.getText().trim(), sex.getText().trim(),
//                number.getText().trim(), email.getText().trim(), addr1.getText().trim(), addr2.getText().trim(),
//                city.getText().trim()};
//        for (String hi: newCustomer)
//        System.out.println(hi + "\n\n");

        try {
            SalesProcessDAO.submitForm(ssn.getText().trim(), newCustomer);
            leadManager.add(fullName.getText().trim()+"%15"+grossly.getText().trim()+"%15"+number.getText().trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String client = "";
//        lead1(client);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}