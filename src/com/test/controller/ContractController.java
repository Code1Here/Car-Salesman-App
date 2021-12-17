package com.test.controller;

import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ContractController extends BaseController implements Initializable {

    @FXML
    private CheckBox CashCheckBox;

    @FXML
    private CheckBox CheckCheckBox;

    @FXML
    private TextField CheckNumberV;

    @FXML
    private TextField CityStateZipV;

    @FXML
    private TextField ColorV;

    @FXML
    private TextField LoanBalanceV;

    @FXML
    private CheckBox LoanCheckBoxV;

    @FXML
    private TextField MakeV;

    @FXML
    private TextField MileageV;

    @FXML
    private TextField ModelV;

    @FXML
    private TextField NumberV;

    @FXML
    private TextField RegistrationV;

    @FXML
    private CheckBox TotalCheckBoxV;

    @FXML
    private TextField TotalV;

    @FXML
    private TextField VINv;

    @FXML
    private TextField YearV;

    @FXML
    private Label approvedLoan;

    @FXML
    private TextField buyerNameV;

    @FXML
    private TextField mailingAddressV;

    @FXML
    void finalize(ActionEvent event) {

    }

    public ContractController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

/*
 import javafx.scene.control.ChoiceBox;
 import javafx.scene.input.MouseEvent;

 @FXML
 private ChoiceBox<String> creditDebit;

 @FXML
 void payment(MouseEvent event) {
 creditDebit.getSelectionModel().getSelectedItem();
 }
 */