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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.test.model.SalesProcessDAO.status;
import static com.test.model.SalesProcessDAO.stamp;

public class DashBoardController extends BaseController implements Initializable {
    public static LinkedList<Customer> customerList = new LinkedList<Customer>();
    public static int index = -1;
    public static boolean triggerInit = true;
    public static boolean trigger2 = false;

    public DashBoardController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    protected LinkedList<String> VehicleManager = new LinkedList<>();
    protected ObservableList<String> vehicleDisplay;

    @FXML
    protected ComboBox<String> productChoice;

    @FXML
    void inventory(ActionEvent event) {
        if (!customerList.isEmpty()){
            if(Objects.equals(productChoice.getValue(), "Tesla cybertruck 2022")){
                Customer temp = customerList.get(index);
                temp.setCarType("cybertruck");
            }
            if(Objects.equals(productChoice.getValue(),"Tesla model x 2021")){
                Customer temp = customerList.get(index);
                temp.setCarType("model x");
            }
            if(Objects.equals(productChoice.getValue(),"Tesla model y 2021")){
                Customer temp = customerList.get(index);
                temp.setCarType("model y");
            }
        }
    }
    @FXML
    void updateDynamic(MouseEvent event) {
        if (triggerInit) {
           switch(index) {
               case 0: update(lead1);
               case 1: update(lead2);
               case 2: update(lead3);
               case 3: update(lead4);
               case 4: update(lead5);
               case 5: update(lead6);
               case 6: update(lead7);
               case 7: update(lead8);
               case 8: update(lead9);
               case 9: update(lead10);
           }
        }
    }

    void update(TextField text) {
        if (triggerInit) {
            if (trigger2) {
                Customer temp = customerList.get(index);
                temp.format();
                text.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                trigger2 = triggerInit = !triggerInit;
                return;
            }
            int tempNum = index;
            switch (index) {
                case 9:
                    Customer temp = customerList.get(index);
                    temp.format();
                    lead10.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 8:
                    temp = customerList.get(index);
                    temp.format();
                    lead9.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 7:
                    temp = customerList.get(index);
                    temp.format();
                    lead8.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 6:
                    temp = customerList.get(index);
                    temp.format();
                    lead7.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 5:
                    temp = customerList.get(index);
                    temp.format();
                    lead6.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 4:
                    temp = customerList.get(index);
                    temp.format();
                    lead5.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 3:
                    temp = customerList.get(index);
                    temp.format();
                    lead4.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 2:
                    temp = customerList.get(index);
                    temp.format();
                    lead3.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 1:
                    temp = customerList.get(index);
                    temp.format();
                    lead2.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
                case 0:
                    temp = customerList.get(index);
                    temp.format();
                    lead1.setText(temp.getName() + temp.getNumber() +"$"+ temp.formatGross());
                    --index;
            }
            triggerInit = !triggerInit;
            index = tempNum;
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
    void closeAction(ActionEvent event) throws SQLException {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SalesProcessDAO.punchIn_punchOut(stamp, status);
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
            e.printStackTrace();
            System.out.println("FAILED:   SalesProcessDAO.inventory(leadManager)");
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
            e.printStackTrace();
            System.out.println("FAILED: SalesProcessDAO.populateFollowUp()");
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
    private Label hint;

    @FXML
    void HintAction(ActionEvent event) {
        hint.setText("(name, number, income)");
    }

    void backgroundBlack(TextField highlight){
        String defaultColor = "-fx-background-color: black; -fx-border-color: white; -fx-text-fill: white;";
        lead1.setStyle(defaultColor);
        lead2.setStyle(defaultColor);
        lead3.setStyle(defaultColor);
        lead4.setStyle(defaultColor);
        lead5.setStyle(defaultColor);
        lead6.setStyle(defaultColor);
        lead7.setStyle(defaultColor);
        lead8.setStyle(defaultColor);
        lead9.setStyle(defaultColor);
        lead10.setStyle(defaultColor);

        highlight.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    }

    @FXML
    void lead1Click(MouseEvent event) {
        if (!customerList.isEmpty()){
            index = 0;
            trigger2 = triggerInit = !triggerInit;
            update(lead1);
            backgroundBlack(lead1);
        }
        System.out.println(customerList.get(0).getName()+" "+customerList.get(0).getCarType());
    }

    @FXML
    void lead2Click(MouseEvent event) {
        if (customerList.size() > 1) {
            index = 1;
            trigger2 = triggerInit = !triggerInit;
            update(lead2);
            backgroundBlack(lead2);
        }
        System.out.println(customerList.get(1).getName()+" "+customerList.get(1).getCarType());
    }

    @FXML
    void lead3Click(MouseEvent event) {
        if (customerList.size() > 2) {
            index = 2;
            trigger2 = triggerInit = !triggerInit;
            update(lead3);
            backgroundBlack(lead3);
        }
    }

    @FXML
    void lead4Click(MouseEvent event) {
        if (customerList.size() > 3) {
            index = 3;
            trigger2 = triggerInit = !triggerInit;
            update(lead4);
            backgroundBlack(lead4);
        }
    }

    @FXML
    void lead5Click(MouseEvent event) {
        if (customerList.size() > 4) {
            index = 4;
            trigger2 = triggerInit = !triggerInit;
            update(lead5);
            backgroundBlack(lead5);
        }
    }

    @FXML
    void lead6Click(MouseEvent event) {
        if (customerList.size() > 5) {
            index = 5;
            trigger2 = triggerInit = !triggerInit;
            update(lead6);
            backgroundBlack(lead6);
        }
    }

    @FXML
    void lead7Click(MouseEvent event) {
        if (customerList.size() > 6) {
            index = 6;
            trigger2 = triggerInit = !triggerInit;
            update(lead7);
            backgroundBlack(lead7);
        }

    }

    @FXML
    void lead8Click(MouseEvent event) {
        if (customerList.size() > 7) {
            index = 7;
            trigger2 = triggerInit = !triggerInit;
            update(lead8);
            backgroundBlack(lead8);
        }
    }

    @FXML
    void lead9Click(MouseEvent event) {
        if (customerList.size() > 8) {
            index = 8;
            trigger2 = triggerInit = !triggerInit;
            update(lead9);
            backgroundBlack(lead9);
        }
    }

    @FXML
    void lead10Click(MouseEvent event) {
        if (customerList.size() > 9) {
            index = 9;
            trigger2 = triggerInit = !triggerInit;
            update(lead10);
            backgroundBlack(lead10);
        }
    }
}