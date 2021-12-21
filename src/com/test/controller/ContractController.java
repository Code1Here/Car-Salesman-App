package com.test.controller;

import com.test.model.Customer;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static com.test.controller.DashBoardController.customerList;
import static com.test.controller.DashBoardController.index;

public class ContractController extends BaseController implements Initializable {
    Customer customer = customerList.get(index);

    public ContractController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private TextField dateOfSale;

    @FXML
    private TextField buyerNameV;

    @FXML
    private TextField mailingAddressV;

    @FXML
    private TextField CityStateZipV;

    @FXML
    private TextField NumberV;

    @FXML
    private TextField VINv;

    @FXML
    private TextField MakeV;

    @FXML
    private TextField ModelV;

    @FXML
    private TextField MileageV;

    @FXML
    private TextField ColorV;

    @FXML
    private TextField YearV;

    @FXML
    private TextField PriceV;

    @FXML
    private Label deposit;

    @FXML
    private CheckBox TotalCheckBoxV;

    @FXML
    private TextField TotalV;

    @FXML
    private TextField LoanBalanceV;

    @FXML
    private CheckBox LoanCheckBoxV;

    @FXML
    private CheckBox CashCheckBox;

    @FXML
    private CheckBox CheckCheckBox;

    @FXML
    private TextField CheckNumberV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateOfSale.setText(String.valueOf(new Date()));

        try {
            ResultSet resultSet = SalesProcessDAO.contractSection1();
            while (resultSet.next()) {
                buyerNameV.setText(resultSet.getString("full_name"));
                mailingAddressV.setText(resultSet.getString("address1"));
                CityStateZipV.setText(resultSet.getString("city") + ", " +
                                      resultSet.getString("state") + ", " +
                                      resultSet.getString("zip"));
                NumberV.setText(resultSet.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILED ->   SalesProcessDAO.contractSection1() ");
        }

        try {
            ResultSet resultSet = SalesProcessDAO.contractSection2();
            while (resultSet.next()) {
                VINv.setText(resultSet.getString("vin"));
                MakeV.setText(resultSet.getString("make"));
                ModelV.setText(resultSet.getString("model"));
                MileageV.setText(resultSet.getString("mileage") + " miles");
                ColorV.setText(resultSet.getString("color"));
                YearV.setText(resultSet.getString("year"));
                PriceV.setText("$"+customer.formatCarPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILED ->   SalesProcessDAO.contractSection2() ");
        }
    }

    @FXML
    void finalize(ActionEvent event) {

    }
}