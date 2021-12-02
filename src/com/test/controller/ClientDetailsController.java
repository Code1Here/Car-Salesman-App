package com.test.controller;

import com.test.model.CarSalesmanDB;
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
        //if (fieldsAreValid()) {
        try {
//PreparedStatement preparedStatement = connection.prepareStatement(
            String query = "INSERT INTO customer_info(ssn, full_name, sex, phone_number, email, address1, address2, " +
                    "city, state, zip, grossly, dob) VALUES('"+ssn.getText().trim()+"', '"+fullName.getText().trim()+"', '"+
                    sex.getText().trim()+"', '"+number.getText().trim()+"', '"+email.getText().trim()+"', '"+addr1.getText().trim()+
                    "', '"+addr2.getText().trim()+"', '"+city.getText().trim()+"', '"+state.getText().trim()+"', '"+zip.getText().trim()+
                    "', '"+grossly.getText().trim()+"', '"+dob.getText().trim()+"');";

            System.out.println(query);

            Connection connection = CarSalesmanDB.dbConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close();
//            while (resultset.next()) {
//                viewFactory.showDashBoardWindow();
//                Stage stage = (Stage) errorLabel.getScene().getWindow();
//                viewFactory.closeStage(stage);
//            } else {
//                JOptionPane.showMessageDialog(null, "Login Failed");
//                emailAddressField.setText("");
//                passwordField.setText("");
//                fullName.requestFocus();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}