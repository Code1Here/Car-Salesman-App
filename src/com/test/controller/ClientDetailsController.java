package com.test.controller;

import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDetailsController extends BaseController implements Initializable {

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
    private TextField sss;

    @FXML
    private TextField state;

    @FXML
    private TextField zip;

    public ClientDetailsController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void finished(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}