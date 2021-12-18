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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class DashBoardController extends BaseController implements Initializable {
    public static LinkedList<Customer> customerList = new LinkedList<Customer>();
    public static int index = -1;
    private boolean triggerInit = true;
    private boolean trigger2 = false;

    public DashBoardController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    protected LinkedList<String> VehicleManager = new LinkedList<>();
    protected ObservableList<String> vehicleDisplay;

    @FXML
    protected ComboBox<String> productChoice;

    @FXML
    void inventory(ActionEvent event) {
        // I don't remember what goes here
    }

    @FXML
    void update(TextField text) {
        if (triggerInit) {
            if (trigger2) {
                Customer temp = customerList.get(index);
                text.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                trigger2 = triggerInit = !triggerInit;
                return;
            }
            switch (index) {
                case 9:
                    Customer temp = customerList.get(index);
                    lead10.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 8:
                    temp = customerList.get(index);
                    lead9.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 7:
                    temp = customerList.get(index);
                    lead8.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 6:
                    temp = customerList.get(index);
                    lead7.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 5:
                    temp = customerList.get(index);
                    lead6.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 4:
                    temp = customerList.get(index);
                    lead5.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 3:
                    temp = customerList.get(index);
                    lead4.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 2:
                    temp = customerList.get(index);
                    lead3.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 1:
                    temp = customerList.get(index);
                    lead2.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
                case 0:
                    temp = customerList.get(index);
                    lead1.setText("Name: " + temp.getName() + "Number: " + temp.getNumber() + "Gross Pay: " + temp.getGrossPay());
                    --index;
            }
            triggerInit = !triggerInit;
        }
    }

    @FXML
    void LoanCalculator(ActionEvent event) {
        viewFactory.showCalculatorWindow();
    }

    @FXML
    void fillOutForm(ActionEvent event) {
        viewFactory.showClientDetailsWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SalesProcessDAO.inventory(VehicleManager);
            vehicleDisplay = FXCollections.observableArrayList(VehicleManager);
            productChoice.setItems(vehicleDisplay);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("FAILED:    Dash...123:   SalesProcessDAO.inventory(leadManager)");
        }

        try {
            ResultSet resultset = SalesProcessDAO.populateFollowUp();

            while (resultset.next()) {
                Customer customer = new Customer(
                        resultset.getString("full_name"),
                        Integer.parseInt(resultset.getString("grossly")),
                        resultset.getString("phone_number"),
                        resultset.getString("ssn"));
                ++index;
                customerList.add(customer);
            }
            update(null);
            triggerInit = !triggerInit;

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("FAILED:    Dash...132:    SalesProcessDAO.populateFollowUp()");
        }
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
        index = 0;
        trigger2 = triggerInit = !triggerInit;
        update(lead1);
    }

    @FXML
    void lead2Click(MouseEvent event) {
        if (customerList.size() >= 1)
            index = 1;
        trigger2 = triggerInit = !triggerInit;
        update(lead2);
    }

    @FXML
    void lead3Click(MouseEvent event) {
        if (customerList.size() >= 2)
            index = 2;
        trigger2 = triggerInit = !triggerInit;
        update(lead3);
    }

    @FXML
    void lead4Click(MouseEvent event) {
        if (customerList.size() >= 3)
            index = 3;
        trigger2 = triggerInit = !triggerInit;
        update(lead4);
    }

    @FXML
    void lead5Click(MouseEvent event) {
        if (customerList.size() >= 4)
            index = 4;
        trigger2 = triggerInit = !triggerInit;
        update(lead5);
    }

    @FXML
    void lead6Click(MouseEvent event) {
        if (customerList.size() >= 5)
            index = 5;
        trigger2 = triggerInit = !triggerInit;
        update(lead6);
    }

    @FXML
    void lead7Click(MouseEvent event) {
        if (customerList.size() >= 6)
            index = 6;
        trigger2 = triggerInit = !triggerInit;
        update(lead7);
    }

    @FXML
    void lead8Click(MouseEvent event) {
        if (customerList.size() >= 7)
            index = 7;
        trigger2 = triggerInit = !triggerInit;
        update(lead8);
    }

    @FXML
    void lead9Click(MouseEvent event) {
        if (customerList.size() >= 8)
            index = 8;
        trigger2 = triggerInit = !triggerInit;
        update(lead9);
    }

    @FXML
    void lead10Click(MouseEvent event) {
        if (customerList.size() >= 9)
            index = 9;
        trigger2 = triggerInit = !triggerInit;
        update(lead10);
    }
}