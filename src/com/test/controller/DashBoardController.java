package com.test.controller;

import com.test.model.Customer;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBoardController extends BaseController implements Initializable {
    public static LinkedList<Customer> customer = new LinkedList<Customer>();
    public static int index;
//    protected LinkedList<String> leadManager = new LinkedList<String>();
//    protected ObservableList<String> leadDisplay;

    /**
     * I need a way to keep track of the text fields in use to put the submitted data into the field
     * while != "" ? fast n dirty
     */

    public DashBoardController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    protected ComboBox<String> productChoice;

    @FXML
    void inventory(ActionEvent event) {

    }

    @FXML
    void LoanCalculator(ActionEvent event) {
        viewFactory.showCalculatorWindow();
    }

    @FXML
    void closeAction(ActionEvent event) {
        Stage stage = (Stage) productChoice.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void optionsAction(ActionEvent event) {
        viewFactory.showOptionsWindow();
    }

    @FXML
    void fillOutForm(ActionEvent event) {
        viewFactory.showClientDetailsWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lead1.setText("");
        lead2.setText("");
        lead3.setText("");
        lead4.setText("");
        lead5.setText("");
        lead6.setText("");
        lead7.setText("");
        lead8.setText("");
        lead9.setText("");
        lead10.setText("");

//        try {
//            SalesProcessDAO.inventory(leadManager);
//            leadDisplay = FXCollections.observableArrayList(leadManager);
//            productChoice.setItems(leadDisplay);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    protected TextField lead1;

    @FXML
    protected TextField lead2;

    @FXML
    protected TextField lead3;

    @FXML
    protected TextField lead4;

    @FXML
    protected TextField lead5;

    @FXML
    protected TextField lead6;

    @FXML
    protected TextField lead7;

    @FXML
    protected TextField lead8;

    @FXML
    protected TextField lead9;

    @FXML
    protected TextField lead10;

    @FXML
    void lead1Click(MouseEvent event) {
        if (!Objects.equals(lead1.getText(), "")) {
            viewFactory.showCalculatorWindow();
        }
    }

    @FXML
    void lead2Click(MouseEvent event) {
        if (!Objects.equals(lead2.getText(), "")) {
            viewFactory.showCalculatorWindow();
        }
    }

    @FXML
    void lead3Click(MouseEvent event) {
        if (!Objects.equals(lead3.getText(), "")) {
            viewFactory.showCalculatorWindow();
        }
    }

    @FXML
    void lead4Click(MouseEvent event) {

    }

    @FXML
    void lead5Click(MouseEvent event) {

    }

    @FXML
    void lead6Click(MouseEvent event) {

    }

    @FXML
    void lead7Click(MouseEvent event) {

    }

    @FXML
    void lead8Click(MouseEvent event) {

    }

    @FXML
    void lead9Click(MouseEvent event) {

    }

    @FXML
    void lead10Click(MouseEvent event) {

    }

    protected void nextLead(String name, String num, String grossPay) {// ALl we want is the name number and grossly
        if(Objects.equals(lead1.getText(), "")){
            lead1.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        } // create an object to store the info or retrieve the key
        if(Objects.equals(lead2.getText(), "")){
            lead2.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead3.getText(), "")){
            lead3.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead4.getText(), "")){
            lead4.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead5.getText(), "")){
            lead5.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead6.getText(), "")){
            lead6.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead7.getText(), "")){
            lead7.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead8.getText(), "")){
            lead8.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead9.getText(), "")){
            lead9.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
            return;
        }
        if(Objects.equals(lead10.getText(), "")){
            lead10.setText("Name: "+name + "Number: "+num + "Gross Pay: "+ grossPay);
        }


            // TODO: you want the info from client submit to dashboard, from inventory pick to dashboard
        // TODO: from dash board w/ grossly to calculator and from calculator to invoice
    }
}