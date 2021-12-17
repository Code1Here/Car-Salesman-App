package com.test.controller;

import com.test.model.CarSalesmanDB;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.test.model.CarSalesmanDB.dbDisconnect;

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

    @FXML
    void loginButtonAction(ActionEvent event)  {

        if (fieldsAreValid()) {
            try {
                if (SalesProcessDAO.loginSuccess(emailAddressField.getText(), passwordField.getText())) {
                    // Login success
                    viewFactory.showDashBoardWindow();
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    viewFactory.closeStage(stage);
                } else {
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
    @FXML
    void clearError(KeyEvent event) {
        errorLabel.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("Goal is met. More goals are next."); // Employee custom tag
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