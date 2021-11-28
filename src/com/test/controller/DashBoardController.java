package com.test.controller;

import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController extends BaseController implements Initializable {
    public DashBoardController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private TextField firstLead;

    @FXML
    void LoanCalculator(ActionEvent event) {
        viewFactory.showCalculatorWindow();
    }
    @FXML
    void closeAction(ActionEvent event) {
        Stage stage = (Stage) firstLead.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void optionsAction(ActionEvent event) {

    }

    @FXML
    void fillOutForm(ActionEvent event) {

    }

    @FXML
    void lead1(MouseEvent event) {

    }

    @FXML
    void lead2(MouseEvent event) {

    }

    @FXML
    void lead3(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}