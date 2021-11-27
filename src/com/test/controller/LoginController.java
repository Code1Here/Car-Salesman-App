package com.test.controller;

import com.test.DBconnection.CarSalesmanDB;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController extends BaseController implements Initializable {

    @FXML
    private TextField emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    public LoginController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    PreparedStatement preparedStatement;
    ResultSet resultset;
    @FXML
    void loginButtonAction(ActionEvent event) {

        if (fieldsAreValid()) {
            try {
                Connection connection = CarSalesmanDB.getDBConnection();

                preparedStatement = connection.prepareStatement("select * from login where username=? and password=?");

                preparedStatement.setString(1, emailAddressField.getText());
                preparedStatement.setString(2, passwordField.getText());

                resultset = preparedStatement.executeQuery();

                if(resultset.next())
                    JOptionPane.showMessageDialog(null, "Login Success");
                else{
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    emailAddressField.setText("");
                    passwordField.setText("");
                    emailAddressField.requestFocus();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private boolean fieldsAreValid() {
        // We will check the contents of our fields
        if (emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please fill email");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill password");
            return false;
        }
        return true;
    }
}